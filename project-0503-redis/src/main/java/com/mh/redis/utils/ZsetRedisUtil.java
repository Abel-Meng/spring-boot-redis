package com.mh.redis.utils;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Set;

/**
 * @author 孟浩
 * @date 2018/5/3  16:30.
 */
@Component
public class ZSetRedisUtil {



    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 新增一个有序集合
     * 如何value 已存在,返回false,同时更新score
     * 如果value 不存在,返回true,添加成功
     *
     * @param key key
     * @param value value
     * @param score score
     * @return boolean
     *
     * ZADD key score1 member1 [score2 member2]
     */
    public Boolean add(String key,Object value, double score){
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    /**
     * 新增一个有序集合 返回添加的成员的数量
     * 如果添加 成员已存在,更新分数 返回0
     *
     * @param key key
     * @param tuples value 和 score的set集合
     * @return long
     *
     * 	ZADD key score1 member1 [score2 member2] 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     */
    public Long add(String key, Set<ZSetOperations.TypedTuple<Object>> tuples){
        return redisTemplate.opsForZSet().add(key, tuples);
    }


    /**
     * 从有序集合中移除一个或者多个元素
     *
     * @param key key
     * @param values 要移除的元素
     * @return 返回移除数量
     *
     * 	ZREM key member [member ...] 移除有序集合中的一个或多个成员
     */
    public Long remove(String key, Object... values){
        return redisTemplate.opsForZSet().remove(key,values);
    }


    /**
     * 增加元素的score值，并返回增加后的值
     *
     * @param key key
     * @param value 元素
     * @param delta 要增加的值
     * @return 增加后的值
     *
     * 	ZINCRBY key increment member 有序集合中对指定成员的分数加上增量 increment
     */
    public Double incrementScore(String key, Object value, double delta){
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }


    /**
     * 返回有序集中指定成员的索引 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param o 指定元素
     * @return 索引
     *
     * 	ZRANK key member
     */
    public Long rank(String key, Object o){
        return redisTemplate.opsForZSet().rank(key, o);
    }


    /**
     * 返回有序集中指定成员的索引，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param o 指定元素
     * @return 索引
     *
     * ZREVRANK key member
     */
    public Long reverseRank(String key, Object o){
       return redisTemplate.opsForZSet().reverseRank(key, o);
    }


    /**
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return 元素集合
     *
     * ZRANGE key start stop
     */
    public Set<Object> range(String key,Long start, Long end){
        return redisTemplate.opsForZSet().range(key,start,end);
    }


    /**
     * 通过索引区间返回有序集合成指定区间内的成员和分数集合，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return 对象(成员,分数)集合
     *
     * ZRANGE key start stop [WITHSCORES]
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeWithScores(String key, Long start, Long end){
        return redisTemplate.opsForZSet().rangeWithScores(key,start,end);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     *
     * ZRANGEBYSCORE key min max
     */
    public Set<Object> rangeByScore(String key,double min,double max){
        return redisTemplate.opsForZSet().rangeByScore(key,min,max);
    }


    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 对象(成员 分数)集合
     *
     * ZRANGEBYSCORE key min max [WITHSCORES]
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key,min,max);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列 可以分页 首页0
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     *
     * ZRANGEBYSCORE key min max [LIMIT]
     */
    public Set<Object> rangeByScore(String key,double min,double max,Long offset, Long count){
        return redisTemplate.opsForZSet().rangeByScore(key,min,max, offset,count);
    }


    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递增(从小到大)顺序排列 可以分页 首页0
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 对象(成员 分数)集合
     *
     * ZRANGEBYSCORE key min max [WITHSCORES] [LIMIT]
     */
    public Set<ZSetOperations.TypedTuple<Object>> rangeByScoreWithScores(String key, double min, double max, Long offset, Long count){
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key,min,max, offset,count);
    }

    /**
     * 通过索引区间返回有序集合成指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return 元素集合
     *
     * ZREVRANGE key start stop
     */
    public Set<Object> reverseRange(String key,Long start, Long end){
        return redisTemplate.opsForZSet().reverseRange(key,start,end);
    }


    /**
     * 通过索引区间返回有序集合成指定区间内的成员和分数集合，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param start 起始索引
     * @param end 结束索引
     * @return 对象(成员,分数)集合
     *
     * ZREVRANGE key start stop [WITHSCORES]
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeWithScores(String key, Long start, Long end){
        return redisTemplate.opsForZSet().reverseRangeWithScores(key,start,end);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     *
     * ZREVRANGEBYSCORE key max min
     */
    public Set<Object> reverseRangeByScore(String key,double min,double max){
        return redisTemplate.opsForZSet().reverseRangeByScore(key,min,max);
    }


    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 对象(成员 分数)集合
     *
     * ZREVRANGEBYSCORE key max min [WITHSCORES]
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeByScoreWithScores(String key, double min, double max){
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key,min,max);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列 可以分页 首页0
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 成员集合
     *
     * ZREVRANGEBYSCORE key max min  [LIMIT]
     */
    public Set<Object> reverseRangeByScore(String key,double min,double max,Long offset, Long count){
        return redisTemplate.opsForZSet().reverseRangeByScore(key,min,max, offset,count);
    }


    /**
     * 通过分数返回有序集合指定区间内的成员，其中有序集成员按分数值递减(从大到小)顺序排列 可以分页 首页0
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return 对象(成员 分数)集合
     *
     * ZREVRANGEBYSCORE key max min [WITHSCORES] [LIMIT]
     */
    public Set<ZSetOperations.TypedTuple<Object>> reverseRangeByScoreWithScores(String key, double min, double max, Long offset, Long count){
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key,min,max, offset,count);
    }

    /**
     * 通过分数返回有序集合指定区间内的成员个数
     *
     * @param key key
     * @param min 最小分数
     * @param max 最大分数
     * @return size
     *
     * zcount key min max
     */
    public Long countByScores(String key,Double min, double max){
        return redisTemplate.opsForZSet().count(key, min, max);
    }


    /**
     *  获取有序集合的成员数
     *  还有size方法,内部调用的同样是 zCard();
     *
     * @param key key
     * @return Long
     *
     * zcard key
     */
    public Long zCard(String key){
        return redisTemplate.opsForZSet().zCard(key);
    }


    /**
     * 获取指定成员的分数
     *
     * @param key key
     * @param o  指定成员
     * @return 分数
     *
     * zscore key member
     */
    public Double score(String key,Object o){
        return redisTemplate.opsForZSet().score(key,o);
    }

    /**
     * 根据索引移除集合成员 其中有序集成员按分数值递增(从小到大)顺序排列
     *
     * @param key key
     * @param start 开始索引
     * @param end 结束索引
     * @return 移除数量
     *
     * ZREMRANGEBYRANK key start stop
     */
    public Long removeRange(String key ,Long start, Long end){
        return redisTemplate.opsForZSet().removeRange(key,start,end);
    }

    /**
     * 根据分数移除集合成员
     *
     * @param key key
     * @param min 开始分数
     * @param max 结束纷纷说
     * @return  移除数量
     *
     * ZREMRANGEBYSCORE key min max
     */
    public Long removeRangeByScore(String key ,double min, double max){
        return redisTemplate.opsForZSet().removeRangeByScore(key,min,max);
    }


    /**
     * 计算给定的一个有序集的并集，并存储在新的 destKey中，key相同的话会把score值相加
     * 返回destKey 对应set集合size
     *
     * @param key key
     * @param otherKeys otherKeys
     * @param destKey destKey
     * @return long
     *
     * ZUNIONSTORE destination numkeys key [key ...]
     */
    public Long unionAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 计算给定的一个有序集的交集，并存储在新的 destKey中，key相同的话会把score值相加
     * 返回destKey 对应zset集合size
     *
     * @param key key
     * @param otherKeys otherKeys
     * @param destKey destKey
     * @return long
     *
     * ZINTERSTORE destination numkeys key [key ...]
     */
    public Long intersectAndStore(String key, Collection<String> otherKeys, String destKey){
        return redisTemplate.opsForZSet().intersectAndStore(key, otherKeys, destKey);
    }


    /**
     * 遍历zset
     *
     * @param key key
     * @param options
     *
     * 	ZSCAN key cursor [MATCH pattern] [COUNT count]
     * 	TODO 需要测试
     */
    public Cursor scan(String key,ScanOptions options){
        return redisTemplate.opsForZSet().scan(key, options);
    }


}
