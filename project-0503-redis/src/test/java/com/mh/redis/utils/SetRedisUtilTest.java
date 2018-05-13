package com.mh.redis.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 孟浩
 * @date 2018/5/7  15:53.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore("not ready yet")
public class SetRedisUtilTest {

    @Resource
    private SetRedisUtil setRedisUtil;

    @Test
    public void add() {
        String [] values = new String[]{"java","Python","C#"};
        Long set1 = setRedisUtil.add("set2", values);
        Assert.assertEquals("3",set1.toString());
    }

    @Test
    public void remove() {
        String [] values = new String[]{"java","C","C++","C"};
        Long set1 = setRedisUtil.remove("set", values);
        Assert.assertEquals("3",set1.toString());
    }

    @Test
    public void pop() {
        Object set = setRedisUtil.pop("set");
        Assert.assertEquals("C#",set);
    }

    @Test
    public void move() {
        Boolean move = setRedisUtil.move("set", "C", "set2");
        Assert.assertEquals(true,move);
    }

    @Test
    public void getSize() {
        Long set = setRedisUtil.getSize("set");
        Assert.assertEquals("4",set.toString());
    }

    @Test
    public void intersect() {
        ArrayList otherKeys = new ArrayList<>();
        otherKeys.add("set2");

        Set set = setRedisUtil.intersect("set", otherKeys);
        for (Object o:set){
            System.out.println(o);
        }

    }

    @Test
    public void intersectAndStore() {
        ArrayList otherKeys = new ArrayList<>();
        otherKeys.add("set2");
        Long aLong = setRedisUtil.intersectAndStore("set", otherKeys, "set3");
        Assert.assertEquals("3",aLong.toString());
    }

    @Test
    public void union() {
        ArrayList otherKeys = new ArrayList<>();
        otherKeys.add("set2");
        otherKeys.add("set3");
        Set set = setRedisUtil.union("set", otherKeys);
        for (Object o : set){
            System.out.println(o);
        }
    }

    @Test
    public void unionAndStore() {
        ArrayList otherKeys = new ArrayList<>();
        otherKeys.add("set2");
        otherKeys.add("set3");
        Long aLong = setRedisUtil.unionAndStore("set", otherKeys, "set4");
        Assert.assertEquals("5",aLong.toString());
    }

    @Test
    public void difference() {
        ArrayList otherKeys = new ArrayList<>();
        otherKeys.add("set2");
        Set set1 = setRedisUtil.difference("set", otherKeys);
        for (Object o:set1){
            System.out.println(o);
        }
    }

    @Test
    public void differenceAndStore() {
        ArrayList otherKeys = new ArrayList<>();
        otherKeys.add("set2");
        Long aLong = setRedisUtil.differenceAndStore("set", otherKeys, "set5");
        Assert.assertEquals("1",aLong.toString());
    }

    @Test
    public void members() {
        Set set = setRedisUtil.members("set");
        for (Object o: set) {
            System.out.println(o);
        }
    }

    @Test
    public void randomMember() {
        Object set = setRedisUtil.randomMember("set");
        System.out.println(set);
    }

    @Test
    public void distinctRandomMembers() {
        Set<Object> set = setRedisUtil.distinctRandomMembers("set", 2L);
        for (Object o:set){
            System.out.println(o);
        }
    }

    @Test
    public void randomMembers() {
        List<Object> set = setRedisUtil.randomMembers("set", 2l);
        for (Object o:set){
            System.out.println(o);
        }
    }

    @Test
    public void scan() {
        Cursor set = setRedisUtil.scan("set", ScanOptions.NONE);
        while(set.hasNext()){
            System.out.println(set.next());
        }
    }
}