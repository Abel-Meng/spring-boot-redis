package com.mh.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author 孟浩
 * @date 2018/5/3  16:29.
 */
public class HashRedisUtil {

    @Resource
    private RedisTemplate redisTemplate;
}
