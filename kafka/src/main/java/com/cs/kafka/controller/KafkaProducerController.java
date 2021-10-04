package com.cs.kafka.controller;

import com.cs.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

@RestController
public class KafkaProducerController {
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @GetMapping("/sync")
    public void sendMessageSync(@RequestParam("message") String message) throws InterruptedException, ExecutionException, TimeoutException {
        kafkaProducerService.sendMessageSync("test",message);
    }

    @GetMapping("/async")
    public void sendMessageAsync(@RequestParam("message") String message){
        kafkaProducerService.sendMessageAsync("test",message);
    }
}
