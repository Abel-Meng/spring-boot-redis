package com.mh.redis.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 孟浩
 * @date 2018/5/7  15:50.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StringRedisUtilTest {

    private String key1 = "test-string-1";
    private String key2 = "test-string-2";
    private String key3 = "test-string-3";

    @Autowired
    private StringRedisUtil stringRedisUtil;

    @Test
    @Ignore("not ready yet")
    public void setStrValue() {
        stringRedisUtil.setStrValue(key1,"name");
    }

    @Test
    @Ignore("not ready yet")
    public void getStrValue() {
        String strValue = stringRedisUtil.getStrValue(key1);
        Assert.assertEquals("name",strValue);
    }

    @Test
    @Ignore("not ready yet")
    public void getChildString() {
        String childString = stringRedisUtil.getChildString(key2, 2, 5);
        Assert.assertEquals("Name",childString);
    }

    @Test
    @Ignore("not ready yet")
    public void getAndSet() {
        String age = stringRedisUtil.getAndSet(key1, "age");
        Assert.assertEquals("name",age);
        Assert.assertEquals("age",stringRedisUtil.getStrValue(key1));
    }

    @Test
    @Ignore("not ready yet")
    public void multiGet() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add(key1);
        strings.add(key2);
        List<String> values = stringRedisUtil.multiGet(strings);

        boolean age = values.contains("age");
        boolean abel = values.contains("MyNameIsAbel");

        Assert.assertEquals(true,age);
        Assert.assertEquals(true,abel);
    }

    @Test
    @Ignore("not ready yet")
    public void setUseTime() {
        stringRedisUtil.setUseTime(key1,"name",3000L, TimeUnit.MILLISECONDS);
    }

    @Test
    @Ignore("not ready yet")
    public void setIfAbsent() {
        stringRedisUtil.setIfAbsent(key2,"name");
        stringRedisUtil.setIfAbsent(key1,"name");
    }

    @Test
    @Ignore("not ready yet")
    public void getSize() {
        Long size = stringRedisUtil.getSize(key3);
        System.out.println(size);
    }

    @Test
    @Ignore("not ready yet")
    public void multiSet() {
        HashMap<String, String> map = new HashMap<>();
        map.put(key1,"name");
        map.put(key2, "age");
        stringRedisUtil.multiSet(map);
    }

    @Test
    @Ignore("not ready yet")
    public void multiSetIfAbsent() {
        HashMap<String, String> map = new HashMap<>();
        map.put(key1,"nameTest");
        map.put(key2, "ageTest");
        stringRedisUtil.multiSetIfAbsent(map);
    }

    @Test
    @Ignore("not ready yet")
    public void incrementLong() {
        Long aLong = stringRedisUtil.incrementLong(key3, 1L);
        Assert.assertEquals("3",aLong.toString());
    }

    @Test
    @Ignore("not ready yet")
    public void incrementDouble() {
        Double aDouble = stringRedisUtil.incrementDouble(key3, 1.1);
         Assert.assertEquals("180510.2",aDouble.toString());
    }

    @Test
    @Ignore("not ready yet")
    public void append() {
        Integer test = stringRedisUtil.append(key1, "test");
        Assert.assertEquals("8",test.toString());
    }

    @Test
    public void delete() {
    }

    @Test
    public void getBit() {
    }

    @Test
    public void setBit() {
    }

    @Test
    public void bitSetValue() {
    }
}