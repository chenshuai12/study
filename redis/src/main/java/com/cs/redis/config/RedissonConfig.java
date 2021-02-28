package com.cs.redis.config;

import com.cs.redis.common.RedissonDistributeLocker;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    /**
     * Redisson客户端注册
     * 单机模式
     */
    @Bean(destroyMethod = "shutdown")
    public RedissonClient createRedissonClient() throws Exception{
        Config config = Config.fromYAML(RedissonConfig.class.getClassLoader().getResource("redisson-config.yml"));
        return Redisson.create(config);
    }

    @Bean
    public RedissonDistributeLocker redissonDistributeLocker(@Autowired RedissonClient redissonClient){
        // redissonClient是本来就由redisson提供给我们，我们创建RedissonDistributeLocker实例交给Spring进行管理
        System.out.println("ad");
        RedissonDistributeLocker locker = new RedissonDistributeLocker(redissonClient);
        return locker;
    }
}
