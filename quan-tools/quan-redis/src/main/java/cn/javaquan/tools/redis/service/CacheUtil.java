package cn.javaquan.tools.redis.service;

/**
 * 自定义缓存工具类
 *
 * @author wangquan
 */
public class CacheUtil {

    private final static int DEFAULT_TIMEOUT = 600;

    private static IRedisService redisService;

    public CacheUtil(IRedisService redisService) {
        CacheUtil.redisService = redisService;
    }

    /**
     * 设置一个对象 可以是string
     *
     * @param key
     * @param obj
     */
    public static <T> void set(final String key, final T obj) {
        redisService.set(key, obj, DEFAULT_TIMEOUT);
    }

    /**
     * 设置一个对象 可以是string
     *
     * @param key
     * @param obj
     * @param seconds 过期时间
     */
    public static <T> void set(final String key, final T obj, final long seconds) {
        redisService.set(key, obj, seconds);
    }

    /**
     * 获取一个对象
     *
     * @param key
     * @param clazz
     */
    public static <T> T get(final String key, Class<T> clazz) {
        return redisService.get(key, clazz);
    }

    public static String get(String key) {
        return redisService.get(key);
    }

    /**
     * 原子递增
     *
     * @param key
     */
    public static Long incr(String key) {
        return redisService.incr(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static void del(final String key) {
        redisService.del(key);
    }
}
