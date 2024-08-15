package cn.javaquan.tools.redis.service;

/**
 * 自定义缓存工具类.
 *
 * @author wangquan
 * @since 1.0.0
 */
public class CacheUtil {

    private static final int DEFAULT_TIMEOUT = 600;

    private static IRedisService redisService;

    /**
     * 构造方法静态的实例.
     * @param redisService redis服务
     */
    public CacheUtil(IRedisService redisService) {
        CacheUtil.redisService = redisService;
    }

    /**
     * 设置一个对象 可以是string.
     * @param key 缓存键
     * @param obj 缓存值
     * @param <T> 缓存值类型
     */
    public static <T> void set(final String key, final T obj) {
        redisService.set(key, obj, DEFAULT_TIMEOUT);
    }

    /**
     * 设置一个对象 可以是string.
     * @param key 缓存键
     * @param obj 缓存值
     * @param seconds 过期时间
     * @param <T> 缓存值类型
     */
    public static <T> void set(final String key, final T obj, final long seconds) {
        redisService.set(key, obj, seconds);
    }

    /**
     * 获取一个对象.
     * @param key 缓存键
     * @param clazz 缓存值类型
     * @param <T> 缓存值类型
     * @return 缓存值
     */
    public static <T> T get(final String key, Class<T> clazz) {
        return redisService.get(key, clazz);
    }

    /**
     * 获取一个字符串格式数据.
     * @param key 缓存键
     * @return 缓存值
     */
    public static String get(String key) {
        return redisService.get(key);
    }

    /**
     * 原子递增.
     * @param key 缓存键
     * @return 递增后的值
     */
    public static Long incr(String key) {
        return redisService.incr(key);
    }

    /**
     * 删除缓存.
     * @param key 缓存键
     */
    public static void del(final String key) {
        redisService.del(key);
    }

}
