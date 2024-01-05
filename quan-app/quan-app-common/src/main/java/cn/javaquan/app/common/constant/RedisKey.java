package cn.javaquan.app.common.constant;


import cn.javaquan.app.common.util.LocalDateUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Redis key 生成
 *
 * @author wangquan
 * @date 2021/12/29 23:07
 */
public class RedisKey {

    public static final String BASE_CACHE_KEY = "quan";

    /**
     * 用户账号KEY
     *
     * @return
     */
    public static String userAccountKey(String account) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":user:account:");
        sb.append(account);
        return sb.toString();
    }

    /**
     * 用户第三方账号KEY
     *
     * @return
     */
    public static String userThirdAccountKey(String thirdId, String thirdType) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":user:account:");
        sb.append(thirdType).append(":");
        sb.append(thirdId);
        return sb.toString();
    }

    /**
     * 用户信息KEY
     *
     * @return
     */
    public static String userInfoKey(String userId) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":user:info:");
        sb.append(userId);
        return sb.toString();
    }

    /**
     * 访问量，按天统计
     *
     * @return
     */
    public static String statisticsAccessKey() {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":statistics:access:").append(LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT));
        return sb.toString();
    }

    /**
     * 浏览统计，当日30分钟内只统计一次
     *
     * @return
     */
    public static String statisticsBrowseKey(String ip) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":statistics:browse:").append(LocalDateUtils.getCurDateTime(LocalDateUtils.STANDARD_DATE_FORMAT)).append(":").append(ip);
        return sb.toString();
    }

    /**
     * 邮箱验证码次数缓存
     *
     * @return
     */
    public static String emailCountKey(String email) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":email:count:");
        sb.append(DigestUtils.md5Hex(email));
        return sb.toString();
    }

    /**
     * 第三方授权缓存
     *
     * @param openId
     * @return
     */
    public static String tripartiteBoundKey(String openId) {
        StringBuffer sb = new StringBuffer(BASE_CACHE_KEY);
        sb.append(":tripartite:bound:");
        sb.append(openId);
        return sb.toString();
    }
}
