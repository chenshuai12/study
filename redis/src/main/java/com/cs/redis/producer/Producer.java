package com.cs.redis.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Producer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ListOperations<String,String> listRedis;
    @PostConstruct
    private void init(){
        listRedis = stringRedisTemplate.opsForList();
    }
    public void publishMessage(String message){
        listRedis.leftPush("message",message);
    }
}
