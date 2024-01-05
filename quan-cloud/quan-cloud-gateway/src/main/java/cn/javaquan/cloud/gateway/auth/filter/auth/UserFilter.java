package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;

/**
 * 用户权限过滤器
 * <p>
 * 该权限要求用户必须登录
 *
 * @author wangquan
 * @date 2020/3/11 16:04
 */
public class UserFilter extends AbstractAuthFilter {

    @Override
    public Result doFilter() {
        String token = getToken(getRequest());

        // 无登录用户信息
        if (StringUtils.isEmpty(token)) {
            return Result.fail(HttpStatus.UNAUTHORIZED.value(), "无访问权限！");
        }

        AuthenticateResponse authRes = getAuth(token);
        if (authRes == null) {
            return Result.fail(HttpStatus.UNAUTHORIZED.value(), "登录信息已失效，请重新登录！");
        }

        // 停止继续执行
        if (!authRes.isExecute()) {
            return Result.fail(HttpStatus.UNAUTHORIZED.value(), "无效的身份令牌");
        }

        return Result.success(authRes.getInfo());
    }

    @Override
    public int getFilterType() {
        // 登录用户
        return AccessorTypeEnum.AUTHENTICATED.getType();
    }
}
