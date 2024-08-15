package cn.javaquan.cloud.gateway.auth.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 认证类型.
 * <p>
 * 3：登录用户；2：带身份；1：匿名
 *
 * @author wangquan
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AccessorTypeEnum {

    /**
     * 所有用户访问.
     */
    ALL(0),

    /**
     * 匿名访问.
     */
    ANON(1),

    /**
     * 非匿名带身份访问.
     */
    NON_ANON(2),

    /**
     * 认证访问.
     */
    AUTHENTICATED(3);

    /**
     * 访问类型.
     */
    final Integer type;

}
