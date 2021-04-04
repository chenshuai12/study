package com.cs.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    /**
     * 定义一个KafkaAdmin的Bean，可以自动检测集群中是否存在topic，不存在则创建
     */
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        /**
         * 指定多个kafka集群多个地址，例如:192.168.2.22:9092,192.168.2.12:9092
         */
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.2.128:9092");
        return new KafkaAdmin(configs);
    }
    /**
     * 创建topic
     */
    public NewTopic topicInfo(){
        //创建topic，需要指定topic的名称，分区，副本
        return new NewTopic("cs",3, (short) 0);
    }
}
