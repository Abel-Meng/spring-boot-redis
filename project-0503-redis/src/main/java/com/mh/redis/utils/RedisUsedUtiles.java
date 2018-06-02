package com.mh.redis.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 孟浩
 * @date 2018/5/14  13:46.
 */
@Component
public class RedisUsedUtiles {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;


    /**
     * 使用redis 生成唯一编码
     *
     * @return 唯一编码
     */
    public String getSerialNo() {

        String preFix = "TEST";
        String now = getNow();
        //创建redis key
        String key = preFix + now;

        String s = stringRedisTemplate.opsForValue().get(key);

        if (null == s) {
            stringRedisTemplate.expire(key, 24L, TimeUnit.HOURS);
            stringRedisTemplate.opsForValue().set(key, String.valueOf(1));
            String sequence = StringUtil.getSequence(1);
            return preFix + now + sequence;
        } else {
            Long increment = stringRedisTemplate.opsForValue().increment(key, 1);
            String sequence = StringUtil.getSequence(increment);
            return preFix + now + sequence;
        }
    }


    /**
     * @param key   - 商品的唯一标志
     * @param value 当前时间+超时时间 也就是时间戳
     * @return boolean
     */
    public boolean lock(String key, String value) {

        //对应setnx命令
        //可以成功设置,也就是key不存在
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        //判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //如果锁过期
        //currentValue不为空且小于当前时间
        //获取上一个锁的时间value
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //对应getset，如果key存在
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);

            //假设两个线程同时进来这里，因为key被占用了，而且锁过期了。
            // 获取的值currentValue=A(get取的旧的值肯定是一样的),两个线程的value都是B,key都是K.锁时间已经过期了。
            //而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的value已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                //oldValue不为空且oldValue等于currentValue，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }


    /**
     * 当前时间格式化yyMMdd
     */
    private String getNow() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        return simpleDateFormat.format(date);
    }
}
