package com.quan.app.pm.bff.system.service;

import com.aliyun.dingtalkcontact_1_0.models.GetUserResponseBody;
import com.quan.common.base.message.Result;
import com.quan.app.common.constant.ErrorCodeEnum;
import com.quan.app.common.constant.RedisKey;
import com.quan.app.common.constant.ThirdTypeEnum;
import com.quan.app.common.module.auth.BoundEvent;
import com.quan.app.common.module.auth.TripartiteAuthEvent;
import com.quan.app.common.module.auth.TripartiteBoundEvent;
import com.quan.app.common.module.auth.TripartiteLoginEvent;
import com.quan.app.common.module.system.SysUserTripartiteAccountAddCommand;
import com.quan.app.common.module.system.SysUserTripartiteAccountDTO;
import com.quan.app.common.module.system.SysUserTripartiteAccountQuery;
import com.quan.app.common.module.system.convert.SystemAssembler;
import com.quan.app.common.util.RunUtil;
import com.quan.app.common.util.Validate;
import com.quan.app.pm.bff.system.convert.TripartiteAssembler;
import com.quan.app.pm.bff.system.feign.SysUserAccountServiceFeign;
import com.quan.app.pm.bff.system.feign.SysUserLoginFeign;
import com.quan.app.pm.bff.system.feign.SysUserTripartiteAccountServiceFeign;
import com.quan.tools.dingtalk.api.DingtalkApi;
import com.quan.tools.redis.service.CacheUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import java.util.function.Function;

/**
 * @author wangquan
 * @version 1.0.0
 * @date 2018-12-03 14:26:22
 */
@RequiredArgsConstructor
@Component
public class TripartiteLoginService {

    private final SysUserTripartiteAccountServiceFeign sysUserTripartiteAccountServiceFeign;

    private final DingtalkApi dingtalkApi;

    private final SysUserAccountServiceFeign sysUserAccountServiceFeign;
    private final SysUserLoginFeign sysUserLoginFeign;

    @Value("${quan.site.domain.default-bound-role:}")
    private Long defaultBoundRole;

    /**
     * 钉钉账号授权
     *
     * @param event
     * @return
     * @throws Exception
     */
    public Result dingtalkAuth(@Valid TripartiteAuthEvent event) throws Exception {
        GetUserResponseBody userResponseBody = dingtalkApi.getAccessToken(event.getAuthCode(), event.getState());

        return login(userResponseBody.getOpenId(), ThirdTypeEnum.DINGTALK.name(), (result) -> {
            String authId = DigestUtils.md5Hex(userResponseBody.getOpenId());
            // 预处理申请绑定参数
            TripartiteBoundEvent boundEvent = TripartiteAssembler.INSTANCE.toDingTalkBoundEvent(userResponseBody);
            CacheUtil.set(RedisKey.tripartiteBoundKey(authId), boundEvent);
            result.setData(authId);
            return Result.success(result);
        });
    }

    /**
     * 用户登录
     * 第三方账号登录
     *
     * @param thirdId   第三方ID
     * @param thirdType 第三方类型
     * @param func
     * @return
     */
    private Result login(String thirdId, String thirdType, Function<Result, Result> func) {
        TripartiteLoginEvent event = TripartiteAssembler.INSTANCE.build(thirdType, thirdId);
        Result result = sysUserLoginFeign.tripartiteLogin(event);
        if (!result.isData()) {
            return func.apply(result);
        }
        return result;
    }

    /**
     * 绑定第三方账号
     *
     * @param boundEvent
     * @return
     */
    public Result<String> bound(BoundEvent boundEvent) {
        String boundCacheKey = RedisKey.tripartiteBoundKey(boundEvent.getAuthId());
        TripartiteBoundEvent tripartiteBoundEvent = CacheUtil.get(boundCacheKey, TripartiteBoundEvent.class);
        Validate.isNotNull(tripartiteBoundEvent, ErrorCodeEnum.TRIPARTITE_NOT_EXIST_ERR);

        SysUserTripartiteAccountQuery query = SystemAssembler.INSTANCE.toSysUserTripartiteAccountQuery(tripartiteBoundEvent.getThirdType(), tripartiteBoundEvent.getThirdId());
        Result<SysUserTripartiteAccountDTO> result = sysUserTripartiteAccountServiceFeign.getByTripartite(query);
        if (result.isData()) {
            SysUserTripartiteAccountDTO tripartiteAccountDTO = result.getData();
            Validate.isFalse(tripartiteAccountDTO.getStatus() == 3, ErrorCodeEnum.TRIPARTITE_EXIST_BOUND_ERR);
            Validate.throwException(ErrorCodeEnum.TRIPARTITE_BOUND_ERR);
        }

        // 当未绑定账号时，绑定账号信息
        SysUserTripartiteAccountAddCommand cmd = TripartiteAssembler.INSTANCE.toTripartiteAccountAddCommand(tripartiteBoundEvent);
        Result boundSaveResult = sysUserTripartiteAccountServiceFeign.save(cmd);
        return RunUtil.doRun(boundSaveResult, () -> {
            CacheUtil.del(boundCacheKey);
            boundSaveResult.setMessage("申请注册成功，请等待管理员审核！");
            return boundSaveResult;
        });
    }

//    /**
//     * 获取token
//     *
//     * @param userId 用户ID
//     * @return
//     */
//    public Result<String> token(String userId) {
//        // 账号登录
//        Result<SysUserAccountDTO> result = sysUserAccountServiceFeign.getUserAccount(SysUserAccountQuery.userId(userId));
//        Validate.isTrue(result.isData(), "账号未注册！");
//        return userLoginServiceFeign.token(userId);
//    }
}

