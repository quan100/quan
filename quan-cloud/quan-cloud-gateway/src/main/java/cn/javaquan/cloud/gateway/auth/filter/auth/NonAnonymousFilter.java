package cn.javaquan.cloud.gateway.auth.filter.auth;

import cn.javaquan.cloud.gateway.auth.constant.AccessorTypeEnum;
import cn.javaquan.common.base.message.Result;
import cn.javaquan.cloud.gateway.auth.filter.AbstractAuthFilter;
import cn.javaquan.security.common.AuthConstant;
import cn.javaquan.security.common.dto.response.AuthenticateResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseCookie;

/**
 * 非匿名权限过滤器
 *
 * @author wangquan
 * @date 2020/3/11 16:00
 */
public class NonAnonymousFilter extends AbstractAuthFilter {

    @Override
    public Result doFilter() {
        String token = getToken(getRequest());

        AuthenticateResponse authRes = getAuth(token);
        if (authRes == null) {
            Result.success();
        }

        // 未能获取到用户token信息
        if (!authRes.isExecute()) {
            return Result.fail("请先登录");
        }

        // 如果jwt非空 保存身份信息到cookie
        if (!StringUtils.isEmpty(authRes.getToken())) {
            ResponseCookie cookie = ResponseCookie.from(AuthConstant.COOKIE_ACCESS_TOKEN, authRes.getToken())
                    .maxAge(2592000)
                    .build();
            getResponse().addCookie(cookie);
        }
        return Result.success();
    }

    @Override
    public int getFilterType() {
        // 带身份
        return AccessorTypeEnum.NON_ANON.getType();
    }
}
