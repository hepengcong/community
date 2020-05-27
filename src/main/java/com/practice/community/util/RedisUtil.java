package com.practice.community.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Iterator;


@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForSet().add(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean hasKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Iterator zRevRank() {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores("viewCounter", 0, Integer.MAX_VALUE, 0, 10).iterator();
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return key == null ? null : (T) redisTemplate.opsForValue().get(key);
    }

    public void incr(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    public void zincr(Integer questionId) {


        //指向key名为KEY的zset元素
        redisTemplate.opsForZSet().incrementScore("viewCounter", questionId, 1);
        //在KEY1中的member为MEMBER1的分数+1

//        redisTemplate.opsForZSet().add("viewCounter", questionId, 1);
    }

}

