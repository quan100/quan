package cn.javaquan.app.common.constant;

import cn.javaquan.app.common.util.LocalDateUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Redis key 生成.
 *
 * @author wangquan
 * @since 1.0.0
 */
public final class RedisKey {

    /**
     * 私有构造方法.
     */
    private RedisKey() {
    }

    /**
     * 基础的缓存键前缀.
     */
    public static final String BASE_CACHE_KEY_PREFIX = "quan";

    /**
     * 用户账号信息缓存键.
     * @param account 用户登录账号
     * @return 用户账号信息缓存键
     */
    public static String userAccountKey(String account) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":user:account:");
        sb.append(account);
        return sb.toString();
    }

    /**
     * 用户第三方账号缓存键.
     * @param thirdId 第三方账号ID
     * @param thirdType 第三方账号类型
     * @return 用户第三方账号缓存键
     */
    public static String userThirdAccountKey(String thirdId, String thirdType) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":user:account:");
        sb.append(thirdType).append(":");
        sb.append(thirdId);
        return sb.toString();
    }

    /**
     * 用户信息缓存键.
     * @param userId 用户ID
     * @return 用户信息缓存键
     */
    public static String userInfoKey(String userId) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":user:info:");
        sb.append(userId);
        return sb.toString();
    }

    /**
     * 用户访问量缓存键.
     * <p>
     * 访问量，按天统计
     * @return 用户访问量缓存键
     */
    public static String statisticsAccessKey() {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":statistics:access:").append(LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT));
        return sb.toString();
    }

    /**
     * 用户浏览量缓存键.
     * <p>
     * 浏览统计，当日30分钟内只统计一次
     * @param ip 用户ip
     * @return 用户浏览量缓存键
     */
    public static String statisticsBrowseKey(String ip) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":statistics:browse:")
            .append(LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT))
            .append(":")
            .append(ip);
        return sb.toString();
    }

    /**
     * 邮箱验证码次数缓存键.
     * @param email 邮箱
     * @return 邮箱验证码次数缓存键
     */
    public static String emailCountKey(String email) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":email:count:");
        sb.append(DigestUtils.md5Hex(email));
        return sb.toString();
    }

    /**
     * 第三方授权缓存键.
     * @param openId 第三方授权ID
     * @return 第三方授权缓存键
     */
    public static String tripartiteBoundKey(String openId) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY_PREFIX);
        sb.append(":tripartite:bound:");
        sb.append(openId);
        return sb.toString();
    }

}
