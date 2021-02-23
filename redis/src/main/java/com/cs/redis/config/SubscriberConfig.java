package com.cs.redis.config;


import com.cs.redis.service.ReceiverService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Configuration
@AutoConfigureAfter({ReceiverService.class})
public class SubscriberConfig {
    /**
     * 注入消息监听适配器
     */
    @Bean
    public MessageListenerAdapter getMessageListenerAdapter(ReceiverService receiverService){
        return new MessageListenerAdapter(receiverService,"receiveMessage");
    }
    /** 消息监听器列表配置 */
    @Bean
    public Map<MessageListener, Collection<? extends Topic>> redisListeners(MessageListenerAdapter messageListenerAdapter) {
        Map<MessageListener, Collection<? extends Topic>> listeners = new HashMap<>();
        Collection<Topic> lstTopic = new ArrayList<>();
        // 可以配置多个监听
        lstTopic.add(new PatternTopic("myChannel"));
        listeners.put(messageListenerAdapter, lstTopic);
        return listeners;
    }
    /**
     * 注入消息监听容器
     */
    @Bean
    public RedisMessageListenerContainer getRedisMessageListenerContainer(
            RedisConnectionFactory redisConnectionFactory,Map<MessageListener, Collection<? extends Topic>> redisListeners){
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);
        redisMessageListenerContainer.setMessageListeners(redisListeners);
        return redisMessageListenerContainer;
    }
}
