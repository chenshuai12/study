package com.cs.redis.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

/**
 * zookeeper分布式锁
 */
@Slf4j
public class ZkLockUtil {

    private static String address = "192.168.2.128:2181";

    public static CuratorFramework client;

    static {
        //baseSleepTimeMs：初始的sleep时间，用于计算之后的每次重试的sleep时间
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        client = CuratorFrameworkFactory.newClient(address,retryPolicy);
        client.start();
    }
    /**
     * 私有的默认构造子，保证外界无法直接实例化
     */
    private ZkLockUtil(){};
    /**
     * 类级的内部内，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装在，从而实现了延迟加载
     * 针对一件商品实现，多件商品同时秒杀建议实现一个map
     */
    private static class SingletonHolder{
        /**
         * 静态初始化器，由jvm来保证线程安全
         */
        private static InterProcessMutex mutex = new InterProcessMutex(client, "/curator/lock");
    }

    public static InterProcessMutex getMutex(){
        return SingletonHolder.mutex;
    }
    //获得了锁

    public static boolean acquire(long time, TimeUnit unit){
        try {
            return getMutex().acquire(time,unit);
        }catch (Exception e){
            log.error("获取锁失败:{}",e);
            return false;
        }
    }
    //释放锁
    public static void release(){
        try {
            getMutex().release();
        }catch (Exception e){
            log.error("释放锁失败:{}",e);
        }
    }
}
