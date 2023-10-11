package com.quan.security.common;

/**
 * @author wangquan
 * @date 2020/3/11 18:37
 */
public class CacheConstants {

    /**
     * 登录尝试次数
     */
    public static final String LOGIN_RETRY_COUNT = "login_retry_count:";

    /**
     * token current
     */
    public static final String USER_TOKEN_CURRENT = "user:token:current:";

    /**
     * 用户key前缀
     */
    public static final String TOKEN_PREFIX = "user:token:";

    /**
     * 保存在请求头中的token
     */
    public static final String HEADER_AUTH_ID = "Authorization";
}
