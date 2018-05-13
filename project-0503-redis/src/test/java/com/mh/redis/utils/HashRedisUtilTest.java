package com.mh.redis.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author 孟浩
 * @date 2018/5/7  15:52.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore("not ready yet")
public class HashRedisUtilTest {

    @Autowired
    private HashRedisUtil hashRedisUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    


    @Test
    public void delete() {
        String[] hashKeys = new String[]{"sex","address"};
        Long zx = hashRedisUtil.delete("zx", hashKeys);
        Assert.assertEquals("0",zx.toString());
    }

    @Test
    public void hasKey() {
        Boolean aBoolean = hashRedisUtil.hasKey("zx", "name");
        Assert.assertEquals(true, aBoolean);
    }

    @Test
    public void get() {
        Object o = hashRedisUtil.get("zx", "name");
        Assert.assertEquals(o,"hjt");
    }

    @Test
    public void entries() {
        Map zx = hashRedisUtil.entries("zx");
        Set set = zx.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void incrementLong() {
        Long aLong = hashRedisUtil.incrementLong("zx", "age", 1L);
        Assert.assertEquals("20",aLong.toString());
    }

    @Test
    public void incrementDouble() {
        Double aDouble = hashRedisUtil.incrementDouble("zx", "age", 1.1D);
        Assert.assertEquals("21.1",aDouble.toString());
    }

    @Test
    public void getKeys() {
        Set<Object> zx = hashRedisUtil.getKeys("zx");
        for (Object o : zx) {
            System.out.println(o);
        }
    }

    @Test
    public void getSize() {
        Long zx = hashRedisUtil.getSize("zx");
        Assert.assertEquals("2",zx.toString());
    }

    @Test
    public void multiGet() {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("name");
        objects.add("age");
        List<Object> zx = hashRedisUtil.multiGet("zx", objects);
        for (Object o : zx) {
            System.out.println(o);
        }
    }

    @Test
    public void putAll() {
        HashMap<String, Object> m = new HashMap<>();
        m.put("name","hjt");
        m.put("age",19);
        m.put("sex",1);
        m.put("address","jx");
        hashRedisUtil.putAll("zx",m);
    }

    @Test
    public void put() {
        String key = "zly";
        String hashKey = "age";
        String value = "18";
        hashRedisUtil.put(key,hashKey,value);
    }

    @Test
    public void putIfAbsent() {
        hashRedisUtil.putIfAbsent("zx","sex",1);

    }

    @Test
    public void getValues() {
        List zly = hashRedisUtil.getValues("zly");
        System.out.println(zly.size() +"--------" + zly.get(0));
    }
}