package com.mh.redis.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 孟浩
 * @date 2018/5/7  15:51.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore("not ready yet")
public class ListRedisUtilTest {

    @Resource
    private ListRedisUtil listRedisUtil;

    @Test
    public void range() {
        List list = listRedisUtil.range("list", 0L, 4L);
        for (Object ss:list){
            System.out.println(ss);
        }
    }

    @Test
    public void trim() {
        listRedisUtil.trim("list",2L,8L);
    }

    @Test
    public void getSize() {
        Long list = listRedisUtil.getSize("list");
        Assert.assertEquals("7",list.toString());
    }

    @Test
    public void leftPush() {
        Long aLong = listRedisUtil.leftPush("list", "C++");
        Assert.assertEquals("2",aLong.toString());
    }

    @Test
    public void rightPush() {
        Long aLong = listRedisUtil.rightPush("list", "C");
        Assert.assertEquals("3",aLong.toString());
    }

    @Test
    public void leftPushIfPresent() {
        Long aLong = listRedisUtil.leftPushIfPresent("list", "C");
        Assert.assertEquals("5",aLong.toString());
    }

    @Test
    public void rightPushIfPresent() {
        Long aLong = listRedisUtil.rightPushIfPresent("list", "Python");
        Assert.assertEquals("6",aLong.toString());
    }

    @Test
    public void leftPush1() {
        Long list = listRedisUtil.leftPush("list", "java", "C#");
        Assert.assertEquals("8",list.toString());
    }

    @Test
    public void rightPush1() {
        Long list = listRedisUtil.rightPush("list", "java", "C#");
        Assert.assertEquals("9",list.toString());
    }

    @Test
    public void setByIndex() {
        listRedisUtil.setByIndex("list",6L,"Python");
    }

    @Test
    public void remove() {
        listRedisUtil.remove("list",0L,"C#");
    }

    @Test
    public void getByIndex() {
        Object list = listRedisUtil.getByIndex("list", (long) 3);
        Assert.assertEquals("Python",list);
    }

    @Test
    public void leftPop() {
        Object list = listRedisUtil.leftPop("list");
        Assert.assertEquals("C++",list);
    }

    @Test
    public void leftPopTime() {
        Object list = listRedisUtil.leftPopTime("list", 2L, TimeUnit.MINUTES);
        Assert.assertEquals("C++",list);
    }

    @Test
    public void rightPopAndLeftPush() {
        Object o = listRedisUtil.rightPopAndLeftPush("list", "list1");
        Assert.assertEquals("Python",o);
    }

    @Test
    public void rightPopAndLeftPushTime() {
        Object o = listRedisUtil.rightPopAndLeftPushTime("list", "list1", 2l, TimeUnit.MINUTES);
        Assert.assertEquals("C",o);
    }
}