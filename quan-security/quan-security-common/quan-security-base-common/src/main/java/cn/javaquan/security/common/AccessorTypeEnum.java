package cn.javaquan.security.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问者认证类型.
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
     * 类型.
     */
    final Integer type;

    /**
     * 比较类型是否相等.
     * @param type 类型
     * @return 类型是否相等
     */
    public boolean eq(Integer type) {
        return this.type.equals(type);
    }

}
