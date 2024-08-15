package cn.javaquan.tools.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作接口.
 *
 * @author wangquan
 * @since 1.0.0
 */
public interface IRedisService {

    /**
     * 获取缓存的对象.
     * @param key 缓存键
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 缓存值
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 获取缓存的字符串.
     * @param key 缓存键
     * @return 缓存值
     */
    String get(String key);

    /**
     * 获取指定键 key 对应值的一个子字符串，从索引 start 到 end（包括 start 和 end），并将该字符串转换为 type类型.
     * @param key 缓存键
     * @param start 子字符串的起始索引
     * @param end 子字符串的结束索引
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 缓存值
     */
    <T> Optional<T> getRange(String key, long start, long end, Class<T> clazz);

    /**
     * 获取指定键 key 对应值的一个子字符串，从索引 start 到 end（包括 start 和 end）.
     * @param key 缓存键
     * @param start 子字符串的起始索引
     * @param end 子字符串的结束索引
     * @return 子字符串
     */
    String getRange(String key, long start, long end);

    /**
     * 更新缓存的值，同时返回之前的旧值.
     * @param key 缓存键
     * @param value 新的值
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 更新前的旧值
     */
    <T> Optional<T> getSet(String key, T value, Class<T> clazz);

    /**
     * 更新缓存的值，同时返回之前的旧值.
     * @param key 缓存键
     * @param value 新的值
     * @return 更新前的旧值
     */
    String getSet(String key, String value);

    /**
     * 设置缓存数据.
     * @param key 缓存键
     * @param value 缓存数据
     * @param <T> 约定缓存的数据类型。
     */
    <T> void set(String key, T value);

    /**
     * 在 Redis 中指定键 key 对应的值中插入或替换从索引 offset 开始的数据.
     * @param key 缓存键
     * @param value 要插入或替换的数据
     * @param offset 插入或替换数据的起始索引
     * @param <T> 约定的数据类型
     */
    <T> void setRange(String key, T value, long offset);

    /**
     * 设置缓存数据，并设置过期时间.
     * <p>
     * 单位：秒
     * @param key 缓存键
     * @param value 要插入或替换的数据
     * @param timeout 过期时间（单位为秒
     * @param <T> 约定的数据类型
     */
    <T> void set(String key, T value, long timeout);

    /**
     * 仅当指定键 key 不存在时设置其值为 value.
     * @param key 缓存键
     * @param value 要设置的数据
     * @param <T> 约定的数据类型
     * @return 设置操作是否成功
     */
    <T> Boolean setNx(String key, T value);

    /**
     * 仅当指定键 key 不存在时设置其值为 value，并且设置过期时间 timeout（单位为秒）.
     * @param key 缓存键
     * @param value 要设置的数据
     * @param timeout 设置的过期时间（单位为秒）
     * @param <T> 约定的数据类型
     * @return 设置操作是否成功
     */
    <T> Boolean setNx(String key, T value, long timeout);

    /**
     * 仅当指定键 key 不存在时设置其值为 value，并且设置过期时间 timeout（自定义时间单位）.
     * @param key 缓存键
     * @param value 要设置的数据
     * @param timeout 设置的过期时间
     * @param unit 过期时间单位
     * @param <T> 约定的数据类型
     * @return 设置操作是否成功
     */
    <T> Boolean setNx(String key, T value, long timeout, TimeUnit unit);

    /**
     * 递增操作.
     * <p>
     * 默认每次递增 1.
     * @param key 缓存键
     * @return 递增后的值
     */
    Long incr(String key);

    /**
     * 递增操作.
     * <p>
     * 自定义每次递增的值.
     * @param key 缓存键
     * @param amount 每次递增的值
     * @return 递增后的值
     */
    Long incrBy(String key, long amount);

    /**
     * 递减操作.
     * <p>
     * 默认每次递减 1.
     * @param key 缓存键
     * @return 递减后的值
     */
    Long decr(String key);

    /**
     * 递减操作.
     * <p>
     * 自定义每次递减的值.
     * @param key 缓存键
     * @param amount 每次递减的值
     * @return 递减后的值
     */
    Long decrBy(String key, long amount);

    /**
     * 设置指定键 key 对应的值中的某个位.
     * <p>
     * 根据给定的偏移量 offset 来设置或清除该位的值为 value.
     * @param key 缓存键
     * @param offset 要设置的位的位置
     * @param value 要设置的位的值
     * @return 设置前的原始值
     */
    Boolean setBit(String key, long offset, boolean value);

    /**
     * 读取指定键 key 对应的值中的某个位.
     * <p>
     * 根据给定的偏移量 offset 来获取该位的值.
     * @param key 缓存键
     * @param offset 要读取的位的位置
     * @return 该位的值
     */
    Boolean getBit(String key, long offset);

    // ~ Lists

    /**
     * 从列表头部取出一个值.并将该值转换为指定的类型.
     * @param key 缓存键
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 列表头部取出的值
     */
    <T> Optional<T> lPop(String key, Class<T> clazz);

    /**
     * 从列表头部取出一个值.
     * @param key 缓存键
     * @return 列表头部取出的值
     */
    String lPop(String key);

    /**
     * 从列表尾部取出一个值.
     * @param key 缓存键
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 列表尾部取出的值
     */
    <T> Optional<T> rPop(String key, Class<T> clazz);

    /**
     * 从列表尾部取出一个值.
     * @param key 缓存键
     * @return 列表尾部取出的值
     */
    String rPop(String key);

    /**
     * 将一个或多个值 value 插入到列表 key 的头部.
     * @param key 缓存键
     * @param values 可变数量的对象参数，这些对象将被添加到列表中.
     * @return 添加元素后的列表长度
     */
    Long lPush(String key, Object... values);

    /**
     * 从 Redis 中指定键 key 对应的列表中获取一个子列表.
     * <p>
     * 接受起始索引 start 和结束索引 end 作为参数，并返回这两个索引之间的元素组成的列表（包括 start 和 end）.
     * @param key 缓存键
     * @param start 子列表的起始索引
     * @param end 子列表的结束索引
     * @return 从 Redis 中获取的子列表
     */
    List<String> lRange(String key, long start, long end);

    /**
     * 获取指定键 key 对应的列表的长度.
     * @param key 缓存键
     * @return 列表的长度
     */
    Long lLen(String key);

    // ~ Hashes

    /**
     * 删除指定键 key 对应的哈希表中的一个或多个字段.
     * @param key 缓存键
     * @param fields 可变数量的字符串参数，这些字段将从哈希表中删除
     * @return 成功删除的字段数量
     */
    Long hDel(String key, String... fields);

    /**
     * 获取指定键 key 对应的哈希表中的字段值.
     * @param key 缓存键
     * @param field 字段名
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 字段值
     */
    <T> Optional<T> hGet(String key, String field, Class<T> clazz);

    /**
     * 获取指定键 key 对应的哈希表中的字段值.
     * @param key 缓存键
     * @param field 字段名
     * @return 字段值
     */
    String hGet(String key, String field);

    /**
     * 获取指定键 key 对应的哈希表中的多个字段值.
     * @param key 缓存键
     * @param fields 可变数量的字符串参数，这些字段的值将从哈希表中批量获取
     * @return 包含所有指定字段值的列表
     */
    List<String> hMGet(String key, String... fields);

    /**
     * 设置指定键 key 对应的哈希表中的字段值.
     * @param key 缓存键
     * @param field 字段名
     * @param value 字段值
     * @param <T> 约定缓存的数据类型
     */
    <T> void hSet(String key, String field, T value);

    /**
     * 设置指定键 key 对应的哈希表中的多个字段值.
     * @param key 缓存键
     * @param fieldValues 键值对集合，其中键是哈希表中的字段，值是字段对应的值
     * @param <T> 约定缓存的数据类型
     */
    <T> void hMSet(String key, Map<String, T> fieldValues);

    /**
     * 获取指定键 key 对应的哈希表中的字段数量.
     * @param key 缓存键
     * @return 字段数量
     */
    Long hLen(String key);

    /**
     * 检查指定键 key 对应的哈希表中是否存在指定字段 field.
     * @param key 缓存键
     * @param field 字段名
     * @return 如果字段存在，则返回 true，否则返回 false
     */
    Boolean hExists(String key, String field);

    /**
     * 获取指定键 key 对应的哈希表中的所有字段和值.
     * @param key 缓存键
     * @param type 指定返回值的类型
     * @param <T> 约定缓存的数据类型
     * @return 包含所有字段和值的映射
     */
    <T> Map<String, T> hGetAll(String key, Class<T> type);

    /**
     * 获取指定键 key 对应的哈希表中的所有字段和值.
     * @param key 缓存键
     * @return 包含所有字段和值的映射
     */
    Map<String, String> hGetAll(String key);

    /**
     * 获取与指定模式 pattern 匹配的所有哈希表的键.
     * @param pattern 用于匹配哈希表键的模式
     * @return 包含所有匹配键的集合
     */
    Set<String> hKeys(String pattern);

    /**
     * 指定键 key 对应的哈希表中，对指定字段 field 的数值进行递增操作. 该方法接受一个增量值 amount，用于增加字段的数值.
     * @param key 缓存键
     * @param field 哈希表中的字段
     * @param amount 要增加的数值
     * @return 更新后的字段值。
     */
    Long hIncr(String key, String field, long amount);

    /**
     * 指定键 key 对应的哈希表中，对指定字段 field 的数值进行递减操作. 该方法接受一个递减值 amount，用于减少字段的数值.
     * @param key 缓存键
     * @param field 哈希表中的字段
     * @param amount 要减少的数值
     * @return 更新后的字段值。
     */
    Long hDecr(String key, String field, long amount);

    /**
     * 指定键 key 对应的哈希表中，对指定字段 field 的数值进行递增操作. 该方法接受一个增量值 amount，用于增加字段的数值.
     * @param key 缓存键
     * @param field 哈希表中的字段
     * @param amount 要增加的数值
     * @return 更新后的字段值。
     */
    Double hIncrFloat(String key, String field, double amount);

    /**
     * 指定键 key 对应的哈希表中，对指定字段 field 的数值进行递减操作. 该方法接受一个递减值 amount，用于减少字段的数值.
     * @param key 缓存键
     * @param field 哈希表中的字段
     * @param amount 要减少的数值
     * @return 更新后的字段值。
     */
    Double hDecrFloat(String key, String field, double amount);

    // ~ Sets

    /**
     * 添加元素到指定键 key 对应的集合（Set）中.
     * @param key 缓存键
     * @param member 要添加到集合中的一个或多个成员
     * @return 成功添加的新成员数量
     */
    Long sAdd(String key, Object... member);

    /**
     * 获取指定键 key 对应的集合（Set）的元素数量.
     * @param key 缓存键
     * @return 集合中元素的数量
     */
    Long sCard(String key);

    /**
     * 从指定键 key 对应的集合（Set）中随机移除并返回一个元素.
     * @param key 缓存键
     * @return 移除的元素
     */
    String sPop(String key);

    /**
     * 从指定键 key 对应的集合（Set）中随机移除并返回一个元素.
     * @param key 缓存键
     * @param type 指定返回值的类型
     * @param <T> 约定缓存的数据类型
     * @return 移除的元素
     */
    <T> Optional<T> sPop(String key, Class<T> type);

    /**
     * 判断指定键 key 对应的集合（Set）中是否存在指定元素 member.
     * @param key 缓存键
     * @param member 要检查的成员
     * @param <T> 约定缓存的数据类型
     * @return 成员是否存在于集合中
     */
    <T> Boolean sIsMember(String key, T member);

    /**
     * 获取指定键 key 对应的集合（Set）中的所有元素.
     * @param key 缓存键
     * @return 集合中的所有元素
     */
    Set<String> sMembers(String key);

    // ~ Sorted Sets

    /**
     * 添加元素到指定键 key 对应的集合（Set）中.
     * @param key 缓存键
     * @param value 要添加到有序集合的元素
     * @param score 元素的分数，用于排序
     * @param <T> 约定缓存的数据类型
     * @return 添加操作是否成功
     */
    <T> Boolean zAdd(String key, T value, double score);

    /**
     * 获取指定键 key 对应的集合（Set）中指定分数区间的元素.
     * @param key 缓存键
     * @param min 分数范围的最小值
     * @param max 分数范围的最大值
     * @return 分数在指定范围内的成员数量
     */
    Long zCount(String key, double min, double max);

    /**
     * 获取获取指定键 key 对应的有序集合（Sorted Set）中指定成员 member 的分数.
     * @param key 缓存键
     * @param member 要查询分数的成员
     * @param <T> 成员的类型
     * @return 成员的分数
     */
    <T> Double zScore(String key, T member);

    /**
     * 移除指定键 key 对应的有序集合（Sorted Set）中指定成员 member.
     * @param key 缓存键
     * @param members 要从有序集合中移除的一个或多个成员
     * @return 成功移除的成员数量
     */
    Long zRem(String key, Object... members);

    /**
     * 获取指定键 key 对应的有序集合（Sorted Set）中指定成员 member 的排名，从低到高.
     * @param key 缓存键
     * @param member 要查询排名的成员
     * @param <T> 成员的类型
     * @return 成员的排名
     */
    <T> Long zRank(String key, T member);

    /**
     * 获取指定键 key 对应的有序集合（Sorted Set）中指定成员 member 的排名，从高到低.
     * @param key 缓存键
     * @param member 要查询逆序排名的成员
     * @param <T> 成员的类型
     * @return 成员的逆序排名
     */
    <T> Long zRevRank(String key, T member);

    /**
     * 获取指定键 key 对应的有序集合（Sorted Set）的元素数量.
     * @param key 缓存键
     * @return 元素数量
     */
    Long zCard(String key);

    /**
     * 获取指定键 key 对应的有序集合（Sorted Set）中指定索引区间的元素.
     * @param key 缓存键
     * @param start 起始索引（包含）
     * @param end 结束索引（包含）
     * @return 指定索引区间的元素
     */
    Set<String> zRange(String key, long start, long end);

    /**
     * 获取指定键 key 对应的有序集合（Sorted Set）中指定分数区间的元素.
     * @param key 缓存键
     * @param min 分数范围的最小值
     * @param max 分数范围的最大值
     * @return 分数在指定范围内的元素
     */
    Set<String> zRangeByScore(String key, double min, double max);

    /**
     * 获取指定键 key 对应的有序集合（Sorted Set）中指定分数区间的元素，按照分数从高到低（逆序）排序.
     * @param key 缓存键
     * @param min 分数范围的最小值
     * @param max 分数范围的最大值
     * @return 分数在指定范围内的成员集合，按分数从高到低排序
     */
    Set<String> zRevRangeByScore(String key, double min, double max);

    // ~ Commons

    /**
     * 删除一个或多个指定 key 对应的缓存.
     * @param keys 要删除的一个或多个 Redis 键
     */
    void del(String... keys);

    /**
     * 设置指定键 key 对应的缓存的过期时间.
     * @param key 缓存键
     * @param timeout 过期时间（以秒为单位）
     * @return 是否设置成功
     */
    Boolean expire(String key, long timeout);

    /**
     * 设置指定键 key 对应的缓存的过期时间.
     * @param timeout 过期时间（以秒为单位）
     * @param keys 要设置过期时间的一个或多个 Redis 键
     * @return 成功设置了过期时间的键的数量
     */
    long expire(long timeout, String... keys);

    /**
     * 判断指定键 key 对应的缓存是否存在.
     * @param key 缓存键
     * @return 键是否存在
     */
    boolean exists(String key);

    /**
     * 从 Redis 中获取所有匹配给定模式 pattern 的键.
     * @param pattern 用于匹配键的模式字符串
     * @return 所有匹配模式的键集合
     */
    Set<String> keys(String pattern);

}
