package com.cs.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Bean
    public Map<String,Object> consumerConfigs(){
        Map<String,Object> propsMap = new HashMap<>();
        /**
         * kafka地址
         */
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"192.168.2.128:9092");
        /**
         * 是否自动提交Offset偏移量(默认true)
         */
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,false);
        /**
         * 自动提交的频率
         */
//        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG,"100");
        /**
         * session超时设置
         */
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG,"15000");
        /**
         * 键的反序列化方式
         */
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        /**
         * 值得反序列化方试
         */
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);

        /**
         * offset偏移量规则设置；
         * （1）earliest:当各分区下已有提交的offset时，从提交的offset开始消费，无提交的offset时，从头开始消费
         * （2）latest:当各分区有已提交的offset时，从提交的offset开始消费，无提交的offset时,消费新产生的该分区下的数据
         *  (3) none:topic各分区下都存在已提交的offset时，从offset后开始消费，只要有一个分区不存在已提交的offset，则抛出异常
         */
        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        /**
         * 每次消费多少记录
         */
        propsMap.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG,100);
        propsMap.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG,10);
        return propsMap;
    }

    @Bean
    public ConsumerFactory<Integer,String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Integer,String>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<Integer,String>
                factory = new ConcurrentKafkaListenerContainerFactory<>();
        /**
         * 设置消费工厂
         */
        factory.setConsumerFactory(consumerFactory());
        /**
         * 消费者组中线程数量
         */
        factory.setConcurrency(3);
        /**
         * 拉取超时时间
         */
        factory.getContainerProperties().setPollTimeout(3000);
        /**
         * 开启批量消费数据
         */
        factory.setBatchListener(true);

        /**
         * 手动提交
         * RECORD： 每处理完一条记录后提交。
         * BATCH(默认)： 每次poll一批数据后提交一次，频率取决于每次poll的调用频率。
         * TIME： 每次间隔ackTime的时间提交。
         * COUNT： 处理完poll的一批数据后并且距离上次提交处理的记录数超过了设置的ackCount就提交。
         * COUNT_TIME： TIME和COUNT中任意一条满足即提交。
         * MANUAL： 手动调用Acknowledgment.acknowledge()后，并且处理完poll的这批数据后提交。
         * MANUAL_IMMEDIATE： 手动调用Acknowledgment.acknowledge()后立即提交。
         */
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);

        // 设置过滤器，只接收消息内容中包含 "test" 的消息
        RecordFilterStrategy recordFilterStrategy = new RecordFilterStrategy() {
            @Override
            public boolean filter(ConsumerRecord consumerRecord) {
                String value = consumerRecord.value().toString();
                if (value !=null && value.contains("test")) {
                    System.err.println(consumerRecord.value());
                    // 返回 false 则接收消息
                    return false;
                }
                // 返回 true 则抛弃消息
                return true;
            }
        };
        // 将过滤器添添加到参数中
        factory.setRecordFilterStrategy(recordFilterStrategy);

        factory.setBatchErrorHandler(kafkaBatchErrorHandler());
        return factory;
    }

    /**
     * 批量息消费异常处理器
     */

    public BatchErrorHandler kafkaBatchErrorHandler(){
        /**
         * 创建 SeekToCurrentBatchErrorHandler 对象
         */
        SeekToCurrentBatchErrorHandler batchErrorHandler = new SeekToCurrentBatchErrorHandler();
        /**
         * 创建 FixedBackOff 对象
         */
        BackOff backOff = new FixedBackOff(10 * 1000L,3L);
        batchErrorHandler.setBackOff(backOff);
        return batchErrorHandler;
    }

}
