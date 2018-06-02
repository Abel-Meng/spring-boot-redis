package com.mh.redis.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 孟浩
 * @date 2018/5/14  14:21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisUsedUtilesTest {

    @Resource
    private RedisUsedUtiles redisUsedUtiles;

    @Test
    public void getSerialNo() {
        String serialNo = redisUsedUtiles.getSerialNo();
        System.out.println(serialNo);
    }
}