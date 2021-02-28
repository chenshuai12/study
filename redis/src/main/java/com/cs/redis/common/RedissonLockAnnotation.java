package com.cs.redis.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 分布式自定义注解
 * 注解在方法
 * @author 陈帅
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedissonLockAnnotation {
    /**
     * 指定组成分布式锁的key
     * @return
     */
    String lockRedisKey();
}
