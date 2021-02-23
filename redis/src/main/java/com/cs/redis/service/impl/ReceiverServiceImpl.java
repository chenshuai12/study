package com.cs.redis.service.impl;

import com.cs.redis.service.ReceiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReceiverServiceImpl implements ReceiverService {
    @Override
    public void receiveMessage(String message) {
        log.info("Receive:{}",message);
    }
}
