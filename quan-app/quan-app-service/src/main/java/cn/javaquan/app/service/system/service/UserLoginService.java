package cn.javaquan.app.service.system.service;

import cn.javaquan.app.service.system.feign.AuthFeign;
import cn.javaquan.app.service.system.feign.SysUserAccountRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysUserInfoRepositoryFeign;
import cn.javaquan.app.service.system.feign.SysUserTripartiteAccountRepositoryFeign;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.app.common.constant.ErrorCodeEnum;
import cn.javaquan.app.common.constant.RedisKey;
import cn.javaquan.app.common.module.auth.EmailLoginCommand;
import cn.javaquan.app.common.module.auth.LoginCommand;
import cn.javaquan.app.common.module.auth.TripartiteLoginEvent;
import cn.javaquan.app.common.module.system.SysUserAccountDTO;
import cn.javaquan.app.common.module.system.SysUserInfoDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountDTO;
import cn.javaquan.app.common.module.system.SysUserTripartiteAccountQuery;
import cn.javaquan.app.common.module.system.convert.SystemAssembler;
import cn.javaquan.app.common.module.user.UserRoleDTO;
import cn.javaquan.app.common.util.LocalDateUtils;
import cn.javaquan.app.common.util.Validate;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.tools.captcha.service.CaptchaService;
import cn.javaquan.tools.crypto.CryptoParam;
import cn.javaquan.tools.crypto.PasswordService;
import cn.javaquan.tools.mail.MailUtil;
import cn.javaquan.tools.redis.service.CacheUtil;
import cn.javaquan.tools.redis.service.IRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户登录服务.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
@Component
public class UserLoginService {

    private final PasswordService passwordService;

    private final SysUserAccountRepositoryFeign userAccountRepository;

    private final SysUserInfoRepositoryFeign userInfoRepository;

    private final AuthFeign authFeign;

    private final IRedisService redisService;

    private final CaptchaService captchaService;

    private final SysUserTripartiteAccountRepositoryFeign userTripartiteAccountRepository;

    private final UserInfoService userInfoService;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Value("${quan.site.domain.title:}")
    private String siteTitle;

    /**
     * 用户登录 账号密码登录.
     * @param cmd 登录请求参数
     * @return 登录成功返回token
     */
    public Result<String> accountLogin(LoginCommand cmd) {
        // 校验验证码
        this.verifyImageCode(cmd.getCaptcha(), cmd.getSessionId());

        // 账号登录
        Result<SysUserAccountDTO> userAccountResult = userAccountRepository
            .getUserAccount(SystemAssembler.INSTANCE.toSysUserAccountQuery(cmd.getAccount(), null));
        Validate.isTrue(userAccountResult.isData(), "用户名或密码错误");
        SysUserAccountDTO sysUserAccountDTO = userAccountResult.getData();

        CryptoParam cryptoParam = new CryptoParam();
        cryptoParam.setAccount(cmd.getAccount());
        cryptoParam.setPassword(cmd.getPassword());
        cryptoParam.setIp(cmd.getIp());
        cryptoParam.setSalt(sysUserAccountDTO.getSalt());
        cryptoParam.setSecret(sysUserAccountDTO.getSecret());
        // 验证密码
        boolean verify = passwordService.verify(cryptoParam);
        Validate.isTrue(verify, "用户名或密码错误");
        verifyStatus(sysUserAccountDTO.getStatus());

        return token(sysUserAccountDTO.getUserId(), sysUserAccountDTO.getAppType());
    }

    /**
     * 用户登录 邮箱登录.
     * @param cmd 登录请求参数
     * @return 登录成功返回token
     */
    public Result<String> emailLogin(EmailLoginCommand cmd) {
        verifyEmailCode(cmd.getPassword(), cmd.getEmail(), cmd.getSessionId());
        Result<SysUserTripartiteAccountDTO> result = userTripartiteAccountRepository.getByEmail(cmd.getEmail());
        return tripartiteLogin(result.getData());
    }

    /**
     * 第三方登录.
     * @param event 登录请求参数
     * @return 登录成功返回token
     */
    public Result<String> tripartiteLogin(TripartiteLoginEvent event) {
        SysUserTripartiteAccountQuery query = SystemAssembler.INSTANCE
            .toSysUserTripartiteAccountQuery(event.getThirdType(), event.getThirdId());
        Result<SysUserTripartiteAccountDTO> result = userTripartiteAccountRepository.getByTripartite(query);
        return tripartiteLogin(result.getData());
    }

    /**
     * 第三方登录.
     * @param tripartiteAccountDTO 登录参数
     * @return 登录成功返回token
     */
    private Result<String> tripartiteLogin(SysUserTripartiteAccountDTO tripartiteAccountDTO) {
        Validate.isNotNull(tripartiteAccountDTO, ErrorCodeEnum.TRIPARTITE_NOT_FIND_ERR);
        verifyStatus(tripartiteAccountDTO.getStatus());
        return token(tripartiteAccountDTO.getUserId());
    }

    /**
     * 获取token.
     * @param userId 用户ID
     * @return token
     */
    private Result<String> token(String userId) {
        // 获取账号信息
        Result<SysUserAccountDTO> result = userAccountRepository
            .getUserAccount(SystemAssembler.INSTANCE.toSysUserAccountQuery(null, userId));
        Validate.isTrue(result.isData(), ErrorCodeEnum.TRIPARTITE_NOT_BOUND_ERR);
        SysUserAccountDTO sysUserAccountDTO = result.getData();
        return token(sysUserAccountDTO.getUserId(), sysUserAccountDTO.getAppType());
    }

    /**
     * 调用权限服务，获取token.
     * @param userId 用户ID
     * @param appType 应用类型
     * @return token
     */
    private Result<String> token(String userId, String appType) {
        // 调用权限服务，保存登录信息，返回token
        Result<SysUserInfoDTO> userInfoResult = userInfoRepository.getUserInfo(userId);
        // 如果用户信息为空，则使用默认信息
        SysUserInfoDTO sysUserInfoDTO = Validate.defaultValue(userInfoResult.getData(),
                SystemAssembler.INSTANCE.toSysUserInfoDTO(userId, siteTitle));

        List<UserRoleDTO> roles = userInfoService.getUserRole(userId);
        AuthEntity authEntity = AuthEntity.toAuthEntity(userId, appType, roles, sysUserInfoDTO);
        // 获取token
        String token = authFeign.token(authEntity);
        Validate.isNotBlank(token, ErrorCodeEnum.LOGIN_ERR);

        return Result.success(token);
    }

    /**
     * 验证账号状态 所有登录账号相关状态统一验证.
     * @param status 用户账号状态
     */
    private void verifyStatus(Integer status) {
        Validate.isFalse(status == 1, ErrorCodeEnum.USER_LOGIN_FREEZE_ERR);
        Validate.isFalse(status == 2, ErrorCodeEnum.USER_LOGIN_LOGOFF_ERR);
        Validate.isFalse(status == 3, ErrorCodeEnum.TRIPARTITE_EXIST_BOUND_ERR);
    }

    /**
     * 退出登录接口.
     * @param token token
     * @return 操作是否成功
     */
    public Result loginOut(String token) {
        authFeign.clean(token);
        return Result.success();
    }

    /**
     * 获取邮箱验证码.
     * @param email 邮箱
     * @param captcha 图形验证码
     * @param sessionId 会话id
     * @return 邮箱验证码获取操作次数
     */
    public Result<Long> verifyAndCreateEmailCode(String email, String captcha, String sessionId) {
        Result result = Result.success();
        String key = RedisKey.emailCountKey(email);

        // 获取次数，当次数大于3时，校验验证码
        // Long count = CacheUtil.incr(RedisKey.emailCountKey(email));
        // if (Validate.isNotBlank(captcha) || count > 3) {
        // Validate.isNotBlank(captcha, ErrorCodeEnum.PARAM_ERROR);
        // checkVerifyCode(captcha, sessionId);
        // }

        try {
            createEmailCode(email, sessionId);
        }
        catch (Exception ex) {
            result = Result.fail(ex.getMessage());
        }
        Long count = CacheUtil.get(key, Long.class);
        result.setData(count);
        return result;
    }

    /**
     * 获取邮箱验证码.
     * @param email 邮箱
     * @param sessionId 会话id
     */
    public void createEmailCode(String email, String sessionId) {
        // 获取绑定信息
        Result<SysUserTripartiteAccountDTO> accountPo = userTripartiteAccountRepository.getByEmail(email);
        Validate.isTrue(accountPo.isData(), ErrorCodeEnum.TRIPARTITE_NOT_FIND_ERR);
        Validate.isFalse(accountPo.getData().getBindStatus() == 0, ErrorCodeEnum.TRIPARTITE_NOT_BOUND_ERR);

        threadPoolTaskExecutor.execute(() -> {
            String code = captchaService.createEmailCode(sessionId, email);
            MailUtil.sendHtml(email, "动态密码登陆", String.format(
                    "您的邮箱（%s）申请动态密码登陆，如确认是本人行为，请正确提交以下动态密码：<span style=\"color:red;font-size:18px;\">%s</span> 服务器发送时间：%s 有效期：%s分钟",
                    email, code, LocalDateUtils.getCurDateTime(), 5));
        });
    }

    /**
     * 校验验证码.
     * @param code 验证码
     * @param sessionId 会话id
     * @return 验证结果
     */
    public Result<?> verifyImageCode(String code, String sessionId) {
        boolean flag = captchaService.verifyImageCode(code, sessionId);
        Validate.isTrue(flag, "验证码错误");

        // 校验成功后删除验证码
        redisService.del(captchaService.getKey(sessionId));
        return Result.success();
    }

    /**
     * 校验邮箱验证码.
     * @param code 验证码
     * @param email 邮箱
     * @param sessionId 会话id
     */
    public void verifyEmailCode(String code, String email, String sessionId) {
        boolean flag = captchaService.verifyEmailCode(code, sessionId, email);
        Validate.isTrue(flag, "动态密码无效，请重新获取");

        // 校验成功后删除验证码
        redisService.del(captchaService.getKey(sessionId, email));
        redisService.del(RedisKey.emailCountKey(email));
        redisService.del(captchaService.getKey(sessionId));
    }

}
