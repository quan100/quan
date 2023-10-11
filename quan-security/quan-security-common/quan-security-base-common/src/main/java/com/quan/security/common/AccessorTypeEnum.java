package com.quan.security.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问者认证类型
 *
 * @author wangquan
 */
@Getter
@AllArgsConstructor
public enum AccessorTypeEnum {

    /**
     * 所有用户访问
     */
    ALL(0),

    /**
     * 匿名访问
     */
    ANON(1),

    /**
     * 非匿名带身份访问
     */
    NON_ANON(2),

    /**
     * 认证访问
     */
    AUTHENTICATED(3);

    Integer type;

    public boolean eq(Integer type) {
        return this.type.equals(type);
    }
}
