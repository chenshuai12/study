package com.cs.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = {"test"},groupId = "cs-kafka")
    public void kafkaListener(@Payload List<String> message,
                              @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                              @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                              Acknowledgment acknowledgment){
        System.out.println(topic + ":" + key + ":" + message);
        /**
         * 手动提交偏移量
         */
        acknowledgment.acknowledge();
    }
}
