package com.mh.redis.utils;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author 孟浩
 * @date 2018/5/3  16:30.
 */
public class SetRedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 将一个或多个成员添加到集合,返回集合size
     *
     * @param key key
     * @param value value
     * @return 集合size
     *
     * SADD key member1 [member2]
     */
    public Long add(String key,Object... value){
        return redisTemplate.opsForSet().add(key,value);
    }


    /**
     * 移除集合中一个或多个成员,返回删除数量
     *
     * @param key key
     * @param values values
     * @return 移除数量
     *
     * SREM key menber [member]  移除一个或多个集合元素
     */
    public Long remove(String key, Object... values){
        return redisTemplate.opsForSet().remove(key,values);
    }

    /**
     * 移除并且返回被移除的元素
     *
     * @param key key
     * @return 被移除元素
     *
     * SPOP key
     */
    public Object pop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将 member 元素从 key 集合移动到 destKey 集合
     *
     * @param key key
     * @param value member元素
     * @param destKey  destKey
     * @return boolean
     *
     * SMOVE source destination member
     */
    public Boolean move(String key, Object value,String destKey){
        return redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 获取set集合长度
     *
     * @param key String
     * @return Long
     *
     * SCARD key
     */
    public Long getSize(String key){
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * key对应的无序集合与一个或多个otherKey对应的无序集合求交集
     *
     * @param key key
     * @param otherkeys 多个key集合
     * @return SET集合
     *
     * SINTER key1 [key2]
     */
    public Set intersect(String key, Collection<Object> otherkeys) {
        return redisTemplate.opsForSet().intersect(key, otherkeys);
    }

    /**
     * 对应的无序集合与一个或多个多个otherKey对应的无序集合求交集存储到destKey无序集合中
     *  返回存储数量
     *
     * @param key key
     * @param otherKeys key集合
     * @param destKey 存储集合key
     * @return long
     *
     * SINTERSTORE destination key1 [key2]
     */
    public Long intersectAndStore(String key,Collection<Object> otherKeys,String destKey){
        return redisTemplate.opsForSet().intersectAndStore(key, otherKeys, destKey);
    }

    /**
     * key对应的无序集合与一个或多个otherKey对应的无序集合求并集
     *
     * @param key key
     * @param otherkeys 多个key集合
     * @return SET集合
     *
     * SUNION key1 [key2]
     */
    public Set union(String key, Collection<Object> otherkeys) {
        return redisTemplate.opsForSet().union(key, otherkeys);
    }

    /**
     * 对应的无序集合与一个或多个多个otherKey对应的无序集合求并集存储到destKey无序集合中
     *  返回存储数量
     *
     * @param key key
     * @param otherKeys key集合
     * @param destKey 存储集合key
     * @return long
     *
     * SUNIONSTORE destination key1 [key2]
     */
    public Long unionAndStore(String key,Collection<Object> otherKeys,String destKey){
        return redisTemplate.opsForSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * key对应的无序集合与一个或多个otherKey对应的无序集合求差集
     *
     * @param key key
     * @param otherkeys 多个key集合
     * @return SET集合
     *
     * SDIFF key1 [key2]
     */
    public Set difference(String key, Collection<Object> otherkeys) {
        return redisTemplate.opsForSet().difference(key, otherkeys);
    }

    /**
     * 对应的无序集合与一个或多个多个otherKey对应的无序集合求差集存储到destKey无序集合中
     *  返回存储数量
     *
     * @param key key
     * @param otherKeys key集合
     * @param destKey 存储集合key
     * @return long
     *
     * SDIFFSTORE destination key1 [key2]
     */
    public Long differenceAndStore(String key,Collection<Object> otherKeys,String destKey){
        return redisTemplate.opsForSet().differenceAndStore(key, otherKeys, destKey);
    }

    /**
     * 获取set集合的所有元素
     *
     * @param key key
     * @return set
     *
     * SMEMBERS key
     */
    public Set members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 随机获取set集合中的一个元素
     *
     * @param key key
     * @return object
     *
     * SRANDMEMBER key
     */
    public Object randomMember(String key){
        return redisTemplate.opsForSet().randomMember(key);
    }


    /**
     * 获取无序集合中的多个元素,count表示格式
     *
     * @param key key
     * @param count 个数
     * @return set 或者list  set去重
     *
     * SRANDMEMBER key [count]
     */
    public Set<Object> distinctRandomMembers(String key, Long count){
        return redisTemplate.opsForSet().distinctRandomMembers(key,count);
    }
    public List<Object> randomMembers(String key, Long count){
        return redisTemplate.opsForSet().randomMembers(key,count);
    }

    /**
     * 遍历set
     *
     * @param key key
     * @param options
     *
     * TODO 需要测试
     */
    public Cursor scan(String key, ScanOptions options){
        return redisTemplate.opsForSet().scan(key,options);
    }

}
