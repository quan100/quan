package cn.javaquan.tools.redis.service.impl;

import cn.javaquan.tools.redis.service.IRedisService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.*;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static org.jooq.lambda.Unchecked.function;
import static org.jooq.lambda.Unchecked.supplier;

/**
 * @author wangquan
 * @date 2020/3/9 23:44
 */
@RequiredArgsConstructor
public class RedisServiceImpl implements IRedisService {

    private final StringRedisTemplate template;
    private final ObjectMapper mapper;

    public <T> T get(String key, Class<T> type) {
        Optional<T> t = toType(get(key), type);
        if (t.isPresent()) {
            return t.get();
        } else {
            return null;
        }
    }

    public String get(String key) {
        return opsForValue().get(key);
    }

    public <T> Optional<T> getRange(String key, long start, long end, Class<T> type) {
        return toType(getRange(key, start, end), type);
    }

    public String getRange(String key, long start, long end) {
        return opsForValue().get(key, start, end);
    }

    public <T> Optional<T> getSet(String key, T value, Class<T> type) {
        return toType(getSet(key, valueToString(value)), type);
    }

    public String getSet(String key, String value) {
        return opsForValue().getAndSet(key, value);
    }

    public <T> void set(String key, T value) {
        opsForValue().set(key, valueToString(value));
    }

    public <T> void setRange(String key, T value, long offset) {
        opsForValue().set(key, valueToString(value), offset);
    }

    public <T> void set(String key, T value, long timeout) {
        opsForValue().set(key, valueToString(value), timeout, TimeUnit.SECONDS);
    }

    public <T> Boolean setNx(String key, T value) {
        return opsForValue().setIfAbsent(key, valueToString(value));
    }

    public <T> Boolean setNx(String key, T value, long timeout) {
        return opsForValue().setIfAbsent(key, valueToString(value), timeout, TimeUnit.SECONDS);
    }

    @Override
    public <T> Boolean setNx(String key, T value, long timeout, TimeUnit unit) {
        return opsForValue().setIfAbsent(key, valueToString(value), timeout, unit);
    }

    public Long incr(String key) {
        return incrBy(key, 1);
    }

    public Long incrBy(String key, long amount) {
        return opsForValue().increment(key, amount);
    }

    public Long decr(String key) {
        return decrBy(key, 1);
    }

    public Long decrBy(String key, long amount) {
        return incrBy(key, -amount);
    }

    public Boolean setBit(String key, long offset, boolean value) {
        return opsForValue().setBit(key, offset, value);
    }

    public Boolean getBit(String key, long offset) {
        return opsForValue().getBit(key, offset);
    }

    //~ Lists

    public <T> Optional<T> lPop(String key, Class<T> type) {
        return toType(lPop(key), type);
    }

    public String lPop(String key) {
        return opsForList().leftPop(key);
    }

    public <T> Optional<T> rPop(String key, Class<T> type) {
        return toType(rPop(key), type);
    }

    public String rPop(String key) {
        return opsForList().rightPop(key);
    }

    @SafeVarargs
    public final <T> Long lPush(String key, T... values) {
        return opsForList().leftPushAll(key, valuesToStrings(values));
    }

    public final List<String> lRange(String key, long start, long end) {
        return opsForList().range(key, start, end);
    }

    public final <T> List<T> lRange(String key, long start, long end, Class<T> type) {
        return toType(lRange(key, start, end), type);
    }

    @SafeVarargs
    public final <T> Long rPush(String key, T... values) {
        return opsForList().rightPushAll(key, valuesToStrings(values));
    }

    public Long lLen(String key) {
        return opsForList().size(key);
    }


    //~ Hashes

    public Long hDel(String key, String... fields) {
        return opsForHash().delete(key, Arrays.stream(fields).toArray());
    }

    public <T> Optional<T> hGet(String key, String field, Class<T> type) {
        return toType(hGet(key, field), type);
    }

    public String hGet(String key, String field) {
        return opsForHash().get(key, field);
    }

    public <T> List<T> hMGet(String key, Class<T> type, String... fields) {
        return toType(hMGet(key, fields), type);
    }

    public List<String> hMGet(String key, String... fields) {
        return opsForHash().multiGet(key, Arrays.asList(fields));
    }

    public <T> void hSet(String key, String field, T value) {
        opsForHash().put(key, field, valueToString(value));
    }

    public <T> void hMSet(String key, Map<String, T> fieldValues) {
        opsForHash().putAll(key, fieldValues.entrySet().stream().collect(toMap(Map.Entry::getKey, function(this::valueToString).compose(Map.Entry::getValue))));
    }

    public Long hLen(String key) {
        return opsForHash().size(key);
    }

    public Boolean hExists(String key, String field) {
        return opsForHash().hasKey(key, field);
    }

    public <T> Map<String, T> hGetAll(String key, Class<T> type) {
        return hGetAll(key).entrySet().stream().collect(toMap(Map.Entry::getKey, castType(type).compose(Map.Entry::getValue)));
    }

    public Map<String, String> hGetAll(String key) {
        return opsForHash().entries(key);
    }

    public Set<String> hKeys(String pattern) {
        return opsForHash().keys(pattern);
    }

    public Long hIncr(String key, String field, long amount) {
        return opsForHash().increment(key, field, amount);
    }

    public Long hDecr(String key, String field, long amount) {
        return opsForHash().increment(key, field, -amount);
    }

    public Double hIncrFloat(String key, String field, double amount) {
        return opsForHash().increment(key, field, amount);
    }

    public Double hDecrFloat(String key, String field, double amount) {
        return opsForHash().increment(key, field, -amount);
    }

    //~ Sets

    @SafeVarargs
    public final <T> Long sAdd(String key, T... member) {
        return opsForSet().add(key, valuesToStrings(member));
    }

    public Long sCard(String key) {
        return opsForSet().size(key);
    }

    public String sPop(String key) {
        return opsForSet().pop(key);
    }

    public <T> Optional<T> sPop(String key, Class<T> type) {
        return toType(sPop(key), type);
    }

    public <T> Boolean sIsMember(String key, T member) {
        return opsForSet().isMember(key, valueToString(member));
    }

    public Set<String> sMembers(String key) {
        return opsForSet().members(key);
    }

    //~ Sorted Sets

    public <T> Boolean zAdd(String key, T value, double score) {
        return opsForZSet().add(key, valueToString(value), score);
    }

    public Long zCount(String key, double min, double max) {
        return opsForZSet().count(key, min, max);
    }

    public <T> Double zScore(String key, T member) {
        return opsForZSet().score(key, member);
    }

    @SafeVarargs
    public final <T> Long zRem(String key, T... members) {
        return opsForZSet().remove(key, Arrays.stream(valuesToStrings(members)).toArray());
    }

    public <T> Long zRank(String key, T member) {
        return opsForZSet().rank(key, valueToString(member));
    }

    public <T> Long zRevRank(String key, T member) {
        return opsForZSet().reverseRank(key, valueToString(member));
    }

    public Long zCard(String key) {
        return opsForZSet().zCard(key);
    }

    public Set<String> zRange(String key, long start, long end) {
        return opsForZSet().range(key, start, end);
    }

    public <T> Set<T> zRange(String key, long start, long end, Class<T> type) {
        return toType(zRange(key, start, end), type);
    }

    public Set<String> zRangeByScore(String key, double min, double max) {
        return opsForZSet().rangeByScore(key, min, max);
    }

    public <T> Set<T> zRangeByScore(String key, double min, double max, Class<T> type) {
        return toType(zRangeByScore(key, min, max), type);
    }

    public Set<String> zRevRangeByScore(String key, double min, double max) {
        return opsForZSet().reverseRangeByScore(key, min, max);
    }

    public <T> Set<T> zRevRangeByScore(String key, double min, double max, Class<T> type) {
        return toType(zRevRangeByScore(key, min, max), type);
    }

    //~ Commons

    public void del(String... keys) {
        template.delete(Arrays.stream(keys).collect(toList()));
    }

    public Boolean expire(String key, long timeout) {
        return template.expire(key, timeout, TimeUnit.SECONDS);
    }

    public long expire(long timeout, String... keys) {
        return Arrays.stream(keys).map(key -> expire(key, timeout)).filter(Boolean::booleanValue).count();
    }

    public boolean exists(String key) {
        return template.hasKey(key);
    }

    public Set<String> keys(String pattern) {
        return template.keys(pattern);
    }

    private <T> Function<String, T> castType(Class<T> type) {
        return function(value -> mapper.readValue(value, type));
    }

    private <T> Optional<T> toType(String value, Class<T> type) {
        return Optional.ofNullable(value).map(castType(type));
    }

    private <T, C extends Collection<T>> C toType(Collection<String> values, Class<T> type) {
        return (C) values.stream().map(castType(type)).collect(toCollection(supplier(values.getClass()::newInstance)));
    }

    private <T> String valueToString(T value) {
        return Option.of(value).filter(String.class::isInstance).map(String.class::cast).getOrElseTry(() -> mapper.writeValueAsString(value));
    }

    private <T> String[] valuesToStrings(T[] values) {
        return Arrays.stream(values).map(this::valueToString).toArray(String[]::new);
    }

    private ValueOperations<String, String> opsForValue() {
        return template.opsForValue();
    }

    private ListOperations<String, String> opsForList() {
        return template.opsForList();
    }

    private HashOperations<String, String, String> opsForHash() {
        return template.opsForHash();
    }

    private SetOperations<String, String> opsForSet() {
        return template.opsForSet();
    }

    private ZSetOperations<String, String> opsForZSet() {
        return template.opsForZSet();
    }

    private GeoOperations<String, String> opsForGeo() {
        return template.opsForGeo();
    }
}

