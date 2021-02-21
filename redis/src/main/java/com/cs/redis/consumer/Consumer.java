package com.cs.redis.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Component
@Slf4j
public class Consumer {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    private ListOperations<String,String> listRedis;
    @PostConstruct
    private void init(){
        listRedis= stringRedisTemplate.opsForList();
    }
    public void receive(){
        while (true){
            String message = listRedis.rightPop("message");
            if (StringUtils.isBlank(message)){
                System.out.println("消息队列为空");
                break;
            }
            System.out.println("message： " + message);
        }
    }

}
