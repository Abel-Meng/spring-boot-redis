package com.mh.redis.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 孟浩
 * @date 2018/5/7  15:53.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore("not ready yet")
public class ZsetRedisUtilTest {

    @Resource
    private ZSetRedisUtil zSetRedisUtil;

    @Test
    public void add() {
        Boolean zset = zSetRedisUtil.add("zset", "C", 0.1);
        Assert.assertEquals(true,zset);
    }

    @Test
    public void add1() {
        Set<ZSetOperations.TypedTuple<Object>> objects = new HashSet<>();
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset",0.3);
        objects.add(objectTypedTuple1);
        Long zset = zSetRedisUtil.add("zset", objects);
        Assert.assertEquals("0",zset.toString());
    }

    @Test
    public void remove() {
        String[] strings = {"zset", "C"};
        Long zset = zSetRedisUtil.remove("zset", strings);
        Assert.assertEquals("2",zset.toString());
    }

    @Test
    public void incrementScore() {
        Double zset = zSetRedisUtil.incrementScore("zset", "C++", 0.2D);
        Assert.assertEquals("1.4",zset.toString());
    }

    @Test
    public void rank() {
        Long zset = zSetRedisUtil.rank("zset", "C++");
        Assert.assertEquals("1",zset.toString());
    }

    @Test
    public void reverseRank() {
        Long zset = zSetRedisUtil.reverseRank("zset", "C++");
        Assert.assertEquals("0",zset.toString());
    }

    @Test
    public void range() {
        Set<Object> zset = zSetRedisUtil.range("zset", 0L, 0L);
        for (Object o: zset){
            System.out.println(o);
        }
    }

    @Test
    public void rangeWithScores() {
        Set<ZSetOperations.TypedTuple<Object>> zset = zSetRedisUtil.rangeWithScores("zset", 0L, 0L);
        for (ZSetOperations.TypedTuple<Object> typedTuple:zset){
            System.out.println(typedTuple.getScore()+ "........"+typedTuple.getValue());
        }
    }

    @Test
    public void rangeByScore() {
        Set<Object> zset = zSetRedisUtil.rangeByScore("zset", 1D, 2D);
        for (Object o : zset){
            System.out.println(o);
        }
    }

    @Test
    public void rangeByScoreWithScores() {
        Set<ZSetOperations.TypedTuple<Object>> zset = zSetRedisUtil.rangeByScoreWithScores("zset", 1d, 2d);
        for (ZSetOperations.TypedTuple<Object> typedTuple:zset){
            System.out.println(typedTuple.getScore()+ "........"+typedTuple.getValue());
        }
    }

    @Test
    public void countByScores() {
        Long zset = zSetRedisUtil.countByScores("zset", 0d, 2d);
        Assert.assertEquals("2",zset.toString());
    }

    @Test
    public void zCard() {
        Long zset = zSetRedisUtil.zCard("zset");
        Assert.assertEquals("2",zset.toString());
    }

    @Test
    public void score() {
        Double zset = zSetRedisUtil.score("zset", "C++");
        Assert.assertEquals("1.4",zset.toString());
    }

    @Test
    public void scan() {
        Cursor<ZSetOperations.TypedTuple<Object>> zset = zSetRedisUtil.scan("zset", ScanOptions.NONE);
        while (zset.hasNext()){
            ZSetOperations.TypedTuple<Object> typedTuple = zset.next();
            System.out.println(typedTuple.getScore() + "+++" +typedTuple.getValue());
        }
    }
}