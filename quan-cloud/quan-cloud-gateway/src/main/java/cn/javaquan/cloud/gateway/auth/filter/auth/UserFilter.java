package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.cloud.gateway.auth.constant.HttpStatusErrorEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import org.apache.commons.lang.StringUtils;

/**
 * 用户权限过滤器.
 * <p>
 * 该权限要求用户必须登录
 *
 * @author wangquan
 * @since 2.3.1
 */
public class UserFilter extends AbstractAuthFilter {

    @Override
    public Result doFilter() {
        String token = getToken(getRequest());

        // 无登录用户信息
        if (StringUtils.isEmpty(token)) {
            return Result.fail(HttpStatusErrorEnum.UNAUTHORIZED);
        }

        AuthenticateResponse authRes = getAuth(token);
        if (authRes == null) {
            return Result.fail(HttpStatusErrorEnum.UNAUTHORIZED);
        }

        // 停止继续执行
        if (!authRes.isExecute()) {
            return Result.fail(HttpStatusErrorEnum.UNAUTHORIZED);
        }

        return Result.success(authRes.getInfo());
    }

    @Override
    public int getFilterType() {
        // 登录用户
        return AccessorTypeEnum.AUTHENTICATED.getType();
    }

}
