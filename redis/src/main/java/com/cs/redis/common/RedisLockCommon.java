package com.cs.redis.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class RedisLockCommon {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * Redis加锁的操作
     */
    public Boolean tryLock(String key,String value){
        //加锁成功
        if (stringRedisTemplate.opsForValue().setIfAbsent(key,value)){
            return true;
        }
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        if (StringUtils.isNotBlank(currentValue) && Long.valueOf(currentValue) < System.currentTimeMillis()){
            //获取上一个锁的时间，如果高并发的情况下可能出现已经被修改的问题，所以多一次判断保证线程的安全
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key,value);
            if (StringUtils.isNotBlank(oldValue) && Objects.equals(oldValue,currentValue)){
                return true;
            }
        }
        return false;
    }

    /**
     * Redis解锁的操作
     */
    public void unLock(String key,String value){
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        try {
            if (StringUtils.isNotBlank(currentValue) && Objects.equals(currentValue,value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("解锁失败,{}",e);
        }
    }


}
