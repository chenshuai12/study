package com.cs.kafka.service;

import com.cs.kafka.config.kafkaSendResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.websocket.SendResult;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Autowired
    private kafkaSendResultHandler kafkaSendResultHandler;

    /**
     * 异步方式发送数据
     * @param topic
     * @param message
     */
    public void sendMessageSync(String topic,String message) throws InterruptedException, ExecutionException, TimeoutException {
        ListenableFuture<SendResult> future = kafkaTemplate.send(topic,message);
        kafkaTemplate.send(topic,"kafka",message).get(10, TimeUnit.SECONDS);
    }

    /**
     * 异步方式发送数据
     * @param topic
     * @param message
     */
    public void sendMessageAsync(String topic,String message){
        kafkaTemplate.setProducerListener(kafkaSendResultHandler);
        kafkaTemplate.send(topic,"kafka",message);
    }
}
