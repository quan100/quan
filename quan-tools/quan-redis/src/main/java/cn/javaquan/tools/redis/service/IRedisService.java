package cn.javaquan.tools.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author wangquan
 * @date 2020/3/9 23:42
 */
public interface IRedisService {

    <T> T get(String key, Class<T> type);

    String get(String key);

    <T> Optional<T> getRange(String key, long start, long end, Class<T> type);

    String getRange(String key, long start, long end);

    <T> Optional<T> getSet(String key, T value, Class<T> type);

    String getSet(String key, String value);

    <T> void set(String key, T value);

    <T> void setRange(String key, T value, long offset);

    <T> void set(String key, T value, long timeout);

    <T> Boolean setNx(String key, T value);

    <T> Boolean setNx(String key, T value, long timeout);

    <T> Boolean setNx(String key, T value, long timeout, TimeUnit unit);

    Long incr(String key);

    Long incrBy(String key, long amount);

    Long decr(String key);

    Long decrBy(String key, long amount);

    Boolean setBit(String key, long offset, boolean value);

    Boolean getBit(String key, long offset);

    //~ Lists

    <T> Optional<T> lPop(String key, Class<T> type);

    String lPop(String key);

    <T> Optional<T> rPop(String key, Class<T> type);

    String rPop(String key);

    <T> Long lPush(String key, T... values);

    List<String> lRange(String key, long start, long end);

    <T> List<T> lRange(String key, long start, long end, Class<T> type);

    <T> Long rPush(String key, T... values);

    Long lLen(String key);

    //~ Hashes

    Long hDel(String key, String... fields);

    <T> Optional<T> hGet(String key, String field, Class<T> type);

    String hGet(String key, String field);

    <T> List<T> hMGet(String key, Class<T> type, String... fields);

    List<String> hMGet(String key, String... fields);

    <T> void hSet(String key, String field, T value);

    <T> void hMSet(String key, Map<String, T> fieldValues);

    Long hLen(String key);

    Boolean hExists(String key, String field);

    <T> Map<String, T> hGetAll(String key, Class<T> type);

    Map<String, String> hGetAll(String key);

    Set<String> hKeys(String pattern);

    Long hIncr(String key, String field, long amount);

    Long hDecr(String key, String field, long amount);

    Double hIncrFloat(String key, String field, double amount);

    Double hDecrFloat(String key, String field, double amount);

    //~ Sets

    <T> Long sAdd(String key, T... member);

    Long sCard(String key);

    String sPop(String key);

    <T> Optional<T> sPop(String key, Class<T> type);

    <T> Boolean sIsMember(String key, T member);

    Set<String> sMembers(String key);

    //~ Sorted Sets

    <T> Boolean zAdd(String key, T value, double score);

    Long zCount(String key, double min, double max);

    <T> Double zScore(String key, T member);

    <T> Long zRem(String key, T... members);

    <T> Long zRank(String key, T member);

    <T> Long zRevRank(String key, T member);

    Long zCard(String key);

    Set<String> zRange(String key, long start, long end);

    <T> Set<T> zRange(String key, long start, long end, Class<T> type);

    Set<String> zRangeByScore(String key, double min, double max);

    <T> Set<T> zRangeByScore(String key, double min, double max, Class<T> type);

    Set<String> zRevRangeByScore(String key, double min, double max);

    <T> Set<T> zRevRangeByScore(String key, double min, double max, Class<T> type);

    //~ Commons

    void del(String... keys);

    Boolean expire(String key, long timeout);

    long expire(long timeout, String... keys);

    boolean exists(String key);

    Set<String> keys(String pattern);

}
