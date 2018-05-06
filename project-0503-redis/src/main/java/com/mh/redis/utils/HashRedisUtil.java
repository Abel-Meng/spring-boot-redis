package com.mh.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 孟浩
 * @date 2018/5/3  16:29.
 */
public class HashRedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 删除一个或多个hash字段
     * 返回删除数量
     *
     * @param key key
     * @param hashKeys hash字段
     * @return 删除数量
     *
     * HDEL key field2 [field2]
     */
    public Long delete(String key,Object... hashKeys){
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    /**
     * 判断是否存在指定哈希字段。
     *
     * @param key key
     * @param hashKey 指定的哈希字段
     * @return boolean
     *
     * HEXISTS key field
     */
    public Boolean hasKey(String key, Object hashKey){
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 获取键下指定哈希字段的值
     *
     * @param key 键
     * @param hashKey 指定哈希字段
     * @return 哈希字段的值
     *
     * HGET key field
     */
    public Object get(String key,Object hashKey){
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 获取存储在指定键的哈希中的所有字段和值
     *
     * @param key key
     * @return map集合
     *
     * HGETALL key
     */
    public Map entries(String key){
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HINCRBY key field increment	将哈希字段的整数值按给定数字增加
     *
     * @param key key
     * @param hashKey 哈希字段
     * @param delta 增加值
     * @return 添加后的值
     */
    public Long incrementLong(String key,Object hashKey,long delta){
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * HINCRBYFLOAT key field increment	将哈希字段的浮点值按给定数值增加
     *
     * @param key key
     * @param hashKey 哈希字段
     * @param delta 增加值
     * @return 添加后的值
     */
    public Double incrementDouble(String key,Object hashKey,Double delta){
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    /**
     * 根据key获取哈希字段的set集合
     *
     * @param key key
     * @return set集合
     *
     * HKEYS key
     */
    public Set<Object> getKeys(String key){
        return redisTemplate.opsForHash().keys(key);
    }

    /**
     * 根据key获取哈希字段数量
     *
     * @param key key
     * @return long
     *
     * HLEN key
     */
    public Long getSize(String key){
        return redisTemplate.opsForHash().size(key);
    }

    /**
     * 返回所有哈希字段的值
     *
     * @param key key
     * @param hashKeys 哈希字段
     * @return list
     *
     * HMGET key field1 [field2]
     */
    public List<Object> multiGet(String key, Collection<Object> hashKeys){
        return redisTemplate.opsForHash().multiGet(key, hashKeys);
    }

    /**
     * 为多个哈希字段设置值
     *
     * @param key key
     * @param m 多个哈希字段的map集合
     *
     *  HMSET key field1 value1 [field2 value2 ]
     */
    public void putAll(String key, Map<? extends  Object,? extends Object> m){
        redisTemplate.opsForHash().putAll(key, m);
    }

    /**
     * 指定哈希字段的值
     *
     * @param key key
     * @param hashKey 哈希字段
     * @param value 值
     *
     * HSET key field value
     */
    public void put(String key,Object hashKey,Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    /**
     * 仅当字段不存在时，才设置哈希字段的值
     * 如果不存在,不操作
     *
     * @param key key
     * @param hashKey 哈希字段
     * @param value 值
     *
     * HSETNX key field value
     */
    public void putIfAbsent(String key,Object hashKey,Object value) {
        redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    /**
     * 返回map中的value集合List；
     *
     * @param key key
     *
     * HVALS key
     */
    public List getValues(String key) {
       return redisTemplate.opsForHash().values(key);
    }

}
