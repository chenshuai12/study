package com.cs.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.ProducerListener;

@Configuration
@Slf4j
public class kafkaSendResultHandler implements ProducerListener {
    @Override
    public void onSuccess(ProducerRecord producerRecord, RecordMetadata recordMetadata){
        log.info("-=-=-=-=================================================");
        log.info(producerRecord.toString() + ":" + recordMetadata.toString());
        log.info("-=-=-=-=================================================");
    }
    @Override
    public void onError(ProducerRecord producerRecord,RecordMetadata recordMetadata, Exception e){
        log.info("-=-=-=-=================================================");
        log.info(producerRecord.toString() + ":" + recordMetadata.toString() + ":" + e);
        log.info("-=-=-=-=================================================");
    }

}
