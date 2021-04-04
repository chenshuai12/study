package com.cs.kafka.service;

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
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate kafkaTemplate;

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
        ListenableFuture<SendResult> future = kafkaTemplate.send(topic,"kafka",message);
        future.addCallback(new ListenableFutureCallback<SendResult>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送kafka失败，入库");
            }

            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("发送kafka成功");
            }
        });
    }
}
