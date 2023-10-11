package com.quan.security.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问者身份类型
 * 根据身份类型生成token
 *
 * @author wangquan
 */
@Getter
@AllArgsConstructor
public enum AudienceEnum {

    /**
     * 匿名用户
     */
    ANON_USER("1"),

    /**
     * 认证用户
     */
    AUTH_USER("2");

    String type;

    public boolean eq(String type) {
        return this.type.equals(type);
    }

}
