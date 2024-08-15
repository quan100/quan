package cn.javaquan.tools.redis.service.impl;

import cn.javaquan.tools.redis.service.IRedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.jooq.lambda.Unchecked;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * redis 操作业务实现.
 *
 * @author wangquan
 * @since 1.0.0
 */
@RequiredArgsConstructor
public class RedisServiceImpl implements IRedisService {

    private final StringRedisTemplate template;

    private final ObjectMapper mapper;

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Optional<T> t = toType(get(key), clazz);
        return t.orElse(null);
    }

    @Override
    public String get(String key) {
        return opsForValue().get(key);
    }

    @Override
    public <T> Optional<T> getRange(String key, long start, long end, Class<T> clazz) {
        return toType(getRange(key, start, end), clazz);
    }

    @Override
    public String getRange(String key, long start, long end) {
        return opsForValue().get(key, start, end);
    }

    @Override
    public <T> Optional<T> getSet(String key, T value, Class<T> clazz) {
        return toType(getSet(key, valueToString(value)), clazz);
    }

    @Override
    public String getSet(String key, String value) {
        return opsForValue().getAndSet(key, value);
    }

    @Override
    public <T> void set(String key, T value) {
        opsForValue().set(key, valueToString(value));
    }

    @Override
    public <T> void setRange(String key, T value, long offset) {
        opsForValue().set(key, valueToString(value), offset);
    }

    @Override
    public <T> void set(String key, T value, long timeout) {
        opsForValue().set(key, valueToString(value), timeout, TimeUnit.SECONDS);
    }

    @Override
    public <T> Boolean setNx(String key, T value) {
        return opsForValue().setIfAbsent(key, valueToString(value));
    }

    @Override
    public <T> Boolean setNx(String key, T value, long timeout) {
        return opsForValue().setIfAbsent(key, valueToString(value), timeout, TimeUnit.SECONDS);
    }

    @Override
    public <T> Boolean setNx(String key, T value, long timeout, TimeUnit unit) {
        return opsForValue().setIfAbsent(key, valueToString(value), timeout, unit);
    }

    @Override
    public Long incr(String key) {
        return incrBy(key, 1);
    }

    @Override
    public Long incrBy(String key, long amount) {
        return opsForValue().increment(key, amount);
    }

    @Override
    public Long decr(String key) {
        return decrBy(key, 1);
    }

    @Override
    public Long decrBy(String key, long amount) {
        return incrBy(key, -amount);
    }

    @Override
    public Boolean setBit(String key, long offset, boolean value) {
        return opsForValue().setBit(key, offset, value);
    }

    @Override
    public Boolean getBit(String key, long offset) {
        return opsForValue().getBit(key, offset);
    }

    // ~ Lists

    @Override
    public <T> Optional<T> lPop(String key, Class<T> clazz) {
        return toType(lPop(key), clazz);
    }

    @Override
    public String lPop(String key) {
        return opsForList().leftPop(key);
    }

    @Override
    public <T> Optional<T> rPop(String key, Class<T> clazz) {
        return toType(rPop(key), clazz);
    }

    @Override
    public String rPop(String key) {
        return opsForList().rightPop(key);
    }

    @Override
    public final Long lPush(String key, Object... values) {
        return opsForList().leftPushAll(key, valuesToStrings(values));
    }

    @Override
    public final List<String> lRange(String key, long start, long end) {
        return opsForList().range(key, start, end);
    }

    @Override
    public Long lLen(String key) {
        return opsForList().size(key);
    }

    // ~ Hashes

    @Override
    public Long hDel(String key, String... fields) {
        return opsForHash().delete(key, Arrays.stream(fields).toArray());
    }

    @Override
    public <T> Optional<T> hGet(String key, String field, Class<T> clazz) {
        return toType(hGet(key, field), clazz);
    }

    @Override
    public String hGet(String key, String field) {
        return opsForHash().get(key, field);
    }

    @Override
    public List<String> hMGet(String key, String... fields) {
        return opsForHash().multiGet(key, Arrays.asList(fields));
    }

    @Override
    public <T> void hSet(String key, String field, T value) {
        opsForHash().put(key, field, valueToString(value));
    }

    @Override
    public <T> void hMSet(String key, Map<String, T> fieldValues) {
        opsForHash().putAll(key,
                fieldValues.entrySet()
                    .stream()
                    .collect(Collectors.toMap(Map.Entry::getKey,
                            Unchecked.function(this::valueToString).compose(Map.Entry::getValue))));
    }

    @Override
    public Long hLen(String key) {
        return opsForHash().size(key);
    }

    @Override
    public Boolean hExists(String key, String field) {
        return opsForHash().hasKey(key, field);
    }

    @Override
    public <T> Map<String, T> hGetAll(String key, Class<T> clazz) {
        return hGetAll(key).entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey, castType(clazz).compose(Map.Entry::getValue)));
    }

    @Override
    public Map<String, String> hGetAll(String key) {
        return opsForHash().entries(key);
    }

    @Override
    public Set<String> hKeys(String pattern) {
        return opsForHash().keys(pattern);
    }

    @Override
    public Long hIncr(String key, String field, long amount) {
        return opsForHash().increment(key, field, amount);
    }

    @Override
    public Long hDecr(String key, String field, long amount) {
        return opsForHash().increment(key, field, -amount);
    }

    @Override
    public Double hIncrFloat(String key, String field, double amount) {
        return opsForHash().increment(key, field, amount);
    }

    @Override
    public Double hDecrFloat(String key, String field, double amount) {
        return opsForHash().increment(key, field, -amount);
    }

    // ~ Sets
    @Override
    public final Long sAdd(String key, Object... member) {
        return opsForSet().add(key, valuesToStrings(member));
    }

    @Override
    public Long sCard(String key) {
        return opsForSet().size(key);
    }

    @Override
    public String sPop(String key) {
        return opsForSet().pop(key);
    }

    @Override
    public <T> Optional<T> sPop(String key, Class<T> clazz) {
        return toType(sPop(key), clazz);
    }

    @Override
    public <T> Boolean sIsMember(String key, T member) {
        return opsForSet().isMember(key, valueToString(member));
    }

    @Override
    public Set<String> sMembers(String key) {
        return opsForSet().members(key);
    }

    // ~ Sorted Sets
    @Override
    public <T> Boolean zAdd(String key, T value, double score) {
        return opsForZSet().add(key, valueToString(value), score);
    }

    @Override
    public Long zCount(String key, double min, double max) {
        return opsForZSet().count(key, min, max);
    }

    @Override
    public <T> Double zScore(String key, T member) {
        return opsForZSet().score(key, member);
    }

    @Override
    public final Long zRem(String key, Object... members) {
        return opsForZSet().remove(key, Arrays.stream(valuesToStrings(members)).toArray());
    }

    @Override
    public <T> Long zRank(String key, T member) {
        return opsForZSet().rank(key, valueToString(member));
    }

    @Override
    public <T> Long zRevRank(String key, T member) {
        return opsForZSet().reverseRank(key, valueToString(member));
    }

    @Override
    public Long zCard(String key) {
        return opsForZSet().zCard(key);
    }

    @Override
    public Set<String> zRange(String key, long start, long end) {
        return opsForZSet().range(key, start, end);
    }

    @Override
    public Set<String> zRangeByScore(String key, double min, double max) {
        return opsForZSet().rangeByScore(key, min, max);
    }

    @Override
    public Set<String> zRevRangeByScore(String key, double min, double max) {
        return opsForZSet().reverseRangeByScore(key, min, max);
    }

    // ~ Commons
    @Override
    public void del(String... keys) {
        template.delete(Arrays.stream(keys).collect(Collectors.toList()));
    }

    @Override
    public Boolean expire(String key, long timeout) {
        return template.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public long expire(long timeout, String... keys) {
        return Arrays.stream(keys).map(key -> expire(key, timeout)).filter(Boolean::booleanValue).count();
    }

    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(template.hasKey(key));
    }

    @Override
    public Set<String> keys(String pattern) {
        return template.keys(pattern);
    }

    /**
     * 将字符串类型的值转换为指定类型 T 的对象.
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 接收一个 String 类型的参数，并返回一个 T 类型的对象的函数
     */
    private <T> Function<String, T> castType(Class<T> clazz) {
        return Unchecked.function(value -> mapper.readValue(value, clazz));
    }

    /**
     * 将字符串类型的值转换为指定类型 T 的对象.
     * @param value 字符串类型的值。
     * @param clazz 类型必须匹配。
     * @param <T> 由这个class对象建模的类的类型。
     * @return 接收一个 String 类型的参数，并返回一个 Optional 类型的对象
     */
    private <T> Optional<T> toType(String value, Class<T> clazz) {
        return Optional.ofNullable(value).map(castType(clazz));
    }

    /**
     * 将指定类型的值转换为字符串类型的值.
     * @param value 指定类型的值。
     * @param <T> 约定的参数类型。
     * @return 接收一个 T 类型的参数，并返回一个 String 类型的对象
     */
    private <T> String valueToString(T value) {
        return Option.of(value)
            .filter(String.class::isInstance)
            .map(String.class::cast)
            .getOrElseTry(() -> mapper.writeValueAsString(value));
    }

    /**
     * 将指定类型的数组转换为字符串类型的数组.
     * @param values 指定类型的数组。
     * @param <T> 约定的参数类型。
     * @return 接收一个 T[] 类型的参数，并返回一个 String[] 类型的对象
     */
    private <T> String[] valuesToStrings(T[] values) {
        return Arrays.stream(values).map(this::valueToString).toArray(String[]::new);
    }

    /**
     * 获取 ValueOperations 对象.
     * @return valueOperations
     */
    private ValueOperations<String, String> opsForValue() {
        return template.opsForValue();
    }

    /**
     * 获取 ListOperations 对象.
     * @return listOperations
     */
    private ListOperations<String, String> opsForList() {
        return template.opsForList();
    }

    /**
     * 获取 HashOperations 对象.
     * @return hashOperations
     */
    private HashOperations<String, String, String> opsForHash() {
        return template.opsForHash();
    }

    /**
     * 获取 SetOperations 对象.
     * @return setOperations
     */
    private SetOperations<String, String> opsForSet() {
        return template.opsForSet();
    }

    /**
     * 获取 ZSetOperations 对象.
     * @return zSetOperations
     */
    private ZSetOperations<String, String> opsForZSet() {
        return template.opsForZSet();
    }

    /**
     * 获取 GeoOperations 对象.
     * @return geoOperations
     */
    private GeoOperations<String, String> opsForGeo() {
        return template.opsForGeo();
    }

}
