package com.mh.redis.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 孟浩
 * @date 2018/5/3  16:15.
 */
@Component
public class StringRedisUtil {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 添加String类型的Key值和String类型的Value
     * @param key key
     * @param value value
     *
     *  SET key value	此命令设置指定键的值。
     *
     */
    public void setStrValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }
    /**
     * 获取String类型的Key值和String类型的Value
     * @param key key
     *
     * GET key	获取指定键的值。
     */
    public String getStrValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取存储在键上的字符串的子字符串 字符串区分大小写
     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return string
     *
     * getrange key start end
     */
    public String getChildString(String key,int start,int end) {
        return stringRedisTemplate.opsForValue().get(key, start, end);
    }

    /**
     * 修改value的值并且返回旧值
     * @param key key
     * @param value value
     * @return value的旧值
     *
     * GETSET key value
     */
    public String getAndSet(String key,String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 获取所有给定键的值
     * @param keys 键的集合
     * @return List<String>
     *
     *  MGET key1 [key2..]
     */
    public List<String> multiGet(List<String> keys) {
         return stringRedisTemplate.opsForValue().multiGet(keys);
    }

    /**
     * 使用键和到期时间来设置值
     * @param key key
     * @param value value
     * @param timeout 到期时间 long类型
     * @param unit 时间类型  SECONDS 秒;MINUTES分;HOURS小时;DAYS天;
     *
     * SETEX key seconds value
     */
    public void setUseTime(String key,String value,Long timeout,TimeUnit unit) {
        //TimeUnit是timeout的类型,如毫秒\秒\天等
        stringRedisTemplate.opsForValue().set(key, value, timeout,unit);
    }

    /**
     * 设置键的值，仅当键不存在时;如果键已存在,则不做任何操作
     * 如果已存在,返回false,如果不存在,返回true.
     *
     * @param key key
     * @param value value
     * @return value
     *
     * SETNX key value
     */
    public Boolean setIfAbsent(String key,String value) {
        return stringRedisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 获取指定键的值的长度
     * @param key key
     * @return long
     *
     * STRLEN key
     */
    public Long getSize(String key){
        return stringRedisTemplate.opsForValue().size(key);
    }

    /**
     * 为多个键设置它们的值
     * @param map 键值的集合
     *
     * MSET key value [key value …]
     */
    public void multiSet(Map<String, String> map){
         stringRedisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 为多个键分别设置它们的值，仅当键不存在时;如果键存在不做任何操作
     *
     * @param m 多个键值的map集合
     * @return boolean
     *
     * MSETNX key value [key value …]
     */
    public Boolean multiSetIfAbsent(Map<? extends String, ? extends String> m){
        return stringRedisTemplate.opsForValue().multiSetIfAbsent(m);
    }

    /**
     * INCR key	将键的整数值增加1
     * INCRBY key increment	将键的整数值按给定的数值增加
     * INCRBYFLOAT key increment	将键的浮点值按给定的数值增加
     * DECR key	将键的整数值减1
     * DECRBY key decrement	按给定数值减少键的整数值
     * @param key key
     * @param delta 增加为正,减少为负
     * @return 增加或减少后的值
     */
    public Long incrementLong(String key,Long delta) {
         return stringRedisTemplate.opsForValue().increment(key, delta);
    }
    public Double incrementDouble(String key,Double delta) {
         return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 在key键对应值的右面追加值value 返回值为追加后value长度
     *
     * @param key key
     * @param value value
     *
     *  APPEND key value
     */
    public Integer append(String key,String value){
        return stringRedisTemplate.opsForValue().append(key, value);
    }

    /**
     * 删除方法
     * @param key key
     * @return boolean
     *
     * del key
     */
    public Boolean delete(String key) {
        return stringRedisTemplate.opsForValue().getOperations().delete(key);
    }





    /**
     * 返回在键处存储的字符串值中偏移处的位值
     * @param key key
     * @param offset 指定偏移量上的位
     * @return boolean
     *
     * GETBIT key offset
     * 这个不知道在什么场景下使用.
     */
    public boolean getBit(String key,Integer offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 存储在键上的字符串值中设置或清除偏移处的位
     * @param key key
     * @param offset 偏移处的位
     * @param value boolean
     * @return boolean
     *
     * SETBIT key offset value
     * 这个不知道在什么场景下使用.
     */
    public Boolean setBit(String key,Integer offset,Boolean value){
        return stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 在指定偏移处开始的键处覆盖字符串的一部分
     *
     * @param key key
     * @param value value
     * @param offset 偏移处
     *
     * SETRANGE key offset value
     * 这个不知道在什么场景下使用.
     */
    public void bitSetValue(String key,String value,long offset){
        stringRedisTemplate.opsForValue().set(key, value,offset);
    }
}
