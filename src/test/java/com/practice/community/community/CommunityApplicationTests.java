package com.practice.community.community;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;
    @Test
    void contextLoads() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        System.out.println(connection.ping());
        redisTemplate.opsForValue().set("key2","hhhhhe");
        System.out.println(redisTemplate.opsForValue().get("key1"));
    }

}
