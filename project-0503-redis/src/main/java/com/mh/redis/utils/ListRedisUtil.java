package com.mh.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 孟浩
 * @date 2018/5/3  16:29.
 */
public class ListRedisUtil {

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 返回存储在键中的列表的指定元素。
     * 偏移开始和停止是基于零的索引，
     * 其中0是列表的第一个元素（列表的头部），1是下一个元素
     *
     * @param key key
     * @param start 开始索引
     * @param end 结束索引 如果是-1,会查询从开始索引到最后一条.
     * @return list集合
     *
     * LRANGE key start stop
     */
    public List range(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key,start,end);
    }


    /**
     * 修剪现有列表，使其只包含指定的指定范围的元素
     * strart 到 end 之间
     *
     * @param key key
     * @param start 起始
     * @param end 结束
     *
     * LTRIM key start stop
     */
    public void trim(String key,long start,long end) {
        redisTemplate.opsForList().trim(key, start, end);
    }

    /**
     * 返回存储在键中的列表的长度。
     * 如果键不存在，则将其解释为空列表，并返回0。
     * 当key存储的值不是列表时返回错误。
     *
     * @param key key
     * @return 集合长度
     *
     * LLEN key
     */
    public Long getSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 将所有指定的值插入存储在键的列表的头部(左)。
     * 如果键不存在，则在执行推送操作之前将其创建为空列表
     * 返回结果为推送后的长度
     *
     * @param key key
     * @param value
     * @return 插入后集合长度
     *
     *  Long leftPushAll(K key, V... values); 添加数组
     *  Long leftPushAll(K key, Collection<V> values); 添加集合
     * lpush key value [value ...]
     */
    public Long leftPush(String key,Object value){
        return redisTemplate.opsForList().leftPush(key, value);
    }


    /**
     * 将所有指定的值插入存储在键的列表的尾部(右)。
     * 如果键不存在，则在执行推送操作之前将其创建为空列表
     * 返回结果为推送后的长度
     *
     * @param key key
     * @param value 插入参数
     * @return 插入后集合长度
     *
     *  Long rightPush(K key, V... values); 添加数组
     *  Long rightPush(K key, Collection<V> values); 添加集合
     *  rpush key value [value ...]
     */
    public Long rightPush(String key,Object value){
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     * 如果不存在,则不会有操作 返回列表集合长度
     *
     * @param key key
     * @param value value
     *
     *  LPUSHX key value
     */
    public Long leftPushIfPresent(String key,Object value){
        return redisTemplate.opsForList().leftPushIfPresent(key, value);
    }

    /**
     * 只有存在key对应的列表才能将这个value值插入到key所对应的列表中
     * 如果不存在,则不会有操作 返回列表集合长度
     *
     * @param key key
     * @param value value
     *
     *  LPUSHX key value
     */
    public Long  rightPushIfPresent(String key,Object value){
        return redisTemplate.opsForList().rightPushIfPresent(key, value);
    }

    /**
     * 插入列表指定元素左边,返回列表集合长度
     *
     * @param key key
     * @param pivot 指定元素
     * @param value 值
     * @return Long
     *
     * LINSERT key BEFORE pivot value
     */
    public Long leftPush(String key,Object pivot,Object value) {
        return redisTemplate.opsForList().leftPush(key, pivot, value);
    }

    /**
     * 插入列表指定元素右边,返回列表集合长度
     *
     * @param key key
     * @param pivot 指定元素
     * @param value 值
     * @return Long
     *
     * LINSERT key AFTER pivot value
     */
    public Long rightPush(String key,Object pivot,Object value) {
        return redisTemplate.opsForList().rightPush(key, pivot, value);
    }


    /**
     * 设置指定索引的值
     *
     * @param key key
     * @param index 索引
     * @param value 值
     *
     * LSET key index value
     */
    public void setByIndex(String key,Long index,Object value){
        redisTemplate.opsForList().set(key,index, value);
    }

    /**
     * 移除key中值为value的i个,返回删除的个数；如果没有这个元素则返回0
     * 计数参数以下列方式影响操作：
     * count> 0：删除等于从头到尾移动的值的元素。
     * count <0：删除等于从尾到头移动的值的元素。
     * count = 0：删除等于value的所有元素。
     *
     * @param key key
     * @param count 移除个数
     * @param value 值
     * @return 移除的个数
     *
     * LREM key count value
     */
    public Long remove(String key,Long count,Object value){
       return redisTemplate.opsForList().remove(key,count,value);
    }

    /**
     * 通过索引获取列表的值
     *
     * @param key key
     * @param index 索引
     * @return 值
     *
     * LINDEX key index
     */
    public Object getByIndex(String key,Long index) {
         return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 删除列表最左边的值 返回删除的值
     *
     * @param key key
     *
     *  LPOP key
     *
     *  RPOP key 从列表尾部删除 leftPop 方法换成rightPop
     */
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移出并获取列表的最后一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param key key
     * @param timeout 超时时间
     * @param unit 时间类型
     * @return 返回删除模板
     *
     * BLPOP key1 [key2 ] timeout 命令中timeout 是秒
     *
     * RLPOP key1 [key2 ] timeout 从列表尾部删除 方法中leftPop换成rightPop
     */
    public Object leftPopTime(String key,Long timeout,TimeUnit unit){
        return redisTemplate.opsForList().leftPop(key, timeout, unit);
    }

    /**
     * 用于移除列表的最后一个元素，
     * 并将该元素添加到另一个列表并返回。
     *
     * @param sourceKey 移除的列表key
     * @param destinationKey 添加元素的列表key
     * @return 元素
     *
     * RPOPLPUSH source destination
     */
    public Object rightPopAndLeftPush(String sourceKey,String destinationKey){
        return redisTemplate.opsForList(). rightPopAndLeftPush(sourceKey, destinationKey);
    }

    /**
     * 用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回，
     *如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param sourceKey 移除的列表key
     * @param destinationKey 添加元素的列表key
     * @param timeout 超时时间
     * @param unit 时间类型
     * @return 元素
     *
     * BRPOPLPUSH source destination timeout
     */
    public Object rightPopAndLeftPushTime(String sourceKey,String destinationKey,Long timeout,TimeUnit unit){
        return redisTemplate.opsForList().rightPopAndLeftPush(sourceKey, destinationKey, timeout, unit);
    }

}
