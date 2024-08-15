package cn.javaquan.security.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 访问者身份类型 根据身份类型生成token.
 *
 * @author wangquan
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum AudienceEnum {

    /**
     * 匿名用户.
     */
    ANON_USER("1"),

    /**
     * 认证用户.
     */
    AUTH_USER("2");

    /**
     * 类型.
     */
    final String type;

    /**
     * 判断类型是否相等.
     * @param type 类型
     * @return 类型是否相等
     */
    public boolean eq(String type) {
        return this.type.equals(type);
    }

}
