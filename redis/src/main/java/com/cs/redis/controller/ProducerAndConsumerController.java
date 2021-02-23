package com.cs.redis.controller;


import com.cs.redis.consumer.Consumer;
import com.cs.redis.producer.Producer;
import com.cs.redis.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produce")
public class ProducerAndConsumerController {
    @Autowired
    private Producer producer;
    @Autowired
    private SendService sendService;
    @Autowired
    private Consumer consumer;
    @RequestMapping("/send/{message}")
    public void send(@PathVariable("message") String message){
//        producer.publishMessage(message);
        sendService.sendMessage(message);
    }
    @RequestMapping("/receive")
    public void receive(){
        consumer.receive();
    }
}
