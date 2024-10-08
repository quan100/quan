package cn.javaquan.security.common.method;

import cn.javaquan.security.common.annotation.AuthUser;
import cn.javaquan.common.base.constant.AppTypeEnum;
import cn.javaquan.security.common.AuthConstant;
import cn.javaquan.security.common.dto.entity.AuthEntity;
import cn.javaquan.security.common.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 用户信息处理程序方法参数解析器.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Slf4j
public class AuthUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 校验是否存在 {@link AuthUser} 注解参数，并且注解参数类型为 {@link AuthEntity}.
     * @param methodParameter 方法参数
     * @return 是否存在 {@link AuthUser} 注解参数，且注解参数类型为 {@link AuthEntity}.
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        AuthUser authUser = methodParameter.getParameterAnnotation(AuthUser.class);
        Class<?> clazz = methodParameter.getParameterType();
        return null != authUser && clazz == AuthEntity.class;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
            NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) {
        AuthUser authUser = methodParameter.getParameterAnnotation(AuthUser.class);
        String token = nativeWebRequest.getHeader(AuthConstant.ACCESS_TOKEN);
        AuthEntity authEntity = AuthUtil.getAuthEntity(token);
        if (null == authEntity) {
            if (authUser.check()) {
                throw new SecurityException("认证信息获取失败！请重新登录！");
            }
            // 匿名用户默认为客户端应用
            return AuthEntity.toAuthEntity(AppTypeEnum.CLIENT_BFF.name());
        }
        return authEntity;
    }

}
