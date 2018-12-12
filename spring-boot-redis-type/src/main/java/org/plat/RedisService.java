package org.plat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 默认过期时长，单位：秒
     */
    public static final long DEFAULT_EXPIRE = 60 * 60 * 24;
    /**
     * 不设置过期时长
     */
    public static final long NOT_EXPIRE = -1L;

    public boolean existsKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 重命名key
     * @param oldKey
     * @param newKey
     */
    public void renameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * newkey不存在时才重命名
     * @param oldKey
     * @param newKey
     * @return
     */
    public boolean renameKeyNotExist(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    /**
     * 删除key
     * @param key
     */
    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除多个key
     * @param keys
     */
    public void deleteKey(String... keys) {
        Set<String> keySet = Stream.of(keys).map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(keySet);
    }

    /**
     * 删除key的集合
     * @param keys
     */
    public void deleteKey(Collection<String> keys) {
        Set<String> keySet = keys.stream().map(k -> k).collect(Collectors.toSet());
        redisTemplate.delete(keySet);
    }

    /**
     * 设置key的生命周期
     * @param key
     * @param time
     * @param unit
     */
    public void expireKey(String key, long time, TimeUnit unit) {
        redisTemplate.expire(key, time, unit);
    }

    /**
     * 指定key在指定日期过期
     * @param key
     * @param date
     */
    public void expireKeyAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 查询key的生命周期
     * @param key
     * @param unit
     * @return
     */
    public long getKeyExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 将KEY设置为永久有效
     * @param key
     */
    public void persistKey(String key) {
        redisTemplate.persist(key);
    }

}
