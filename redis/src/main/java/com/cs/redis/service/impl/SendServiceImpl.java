package com.cs.redis.service.impl;

import com.cs.redis.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendServiceImpl implements SendService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void sendMessage(String message) {
        log.info("发送消息：{}",message);
        stringRedisTemplate.convertAndSend("myChannel",message);
    }
}
