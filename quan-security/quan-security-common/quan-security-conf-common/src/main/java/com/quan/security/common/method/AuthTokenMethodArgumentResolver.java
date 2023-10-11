package com.quan.security.common.method;

import com.quan.security.common.AuthConstant;
import com.quan.security.common.annotation.AuthToken;
import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 认证TOKEN处理程序方法参数解析器
 *
 * @author wangquan
 */
public class AuthTokenMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 校验是否存在 {@link AuthToken} 注解参数，并且注解参数类型为 {@link String}
     *
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        AuthToken authToken = methodParameter.getParameterAnnotation(AuthToken.class);
        Class clazz = methodParameter.getParameterType();
        return null != authToken && clazz == String.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader(AuthConstant.ACCESS_TOKEN);
        if (StringUtils.isEmpty(token)) {
            throw new SecurityException("非法的请求！");
        }
        return token;
    }
}
