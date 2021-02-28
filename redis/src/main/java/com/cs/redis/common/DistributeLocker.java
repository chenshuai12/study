package com.cs.redis.common;

import java.util.concurrent.TimeUnit;

public interface DistributeLocker {

    /**
     * 加锁
     * @param locKey
     */
    void lock(String locKey);

    /**
     * 加锁，设置有效期
     * @param lockKey
     * @param timeout
     */
    void lock(String lockKey,int timeout);

    /**
     * 加锁，设置有效期并指定时间单位
     * @param lockKey
     * @param timeout
     * @param unit
     */
    void lock(String lockKey, int timeout, TimeUnit unit);

    /**
     * 释放锁
     * @param lockKey
     */
    void unlock(String lockKey);

    /**
     * 尝试获取锁，获取到则持有该锁并返回true，未获取到则返回false
     * @param lockKey
     * @return
     */
    boolean tryLock(String lockKey);

    /**
     * 尝试获取锁，获取到则持有该锁leaseTime时间
     * 若未获取到，在waitTime时间内一直尝试获取，超过waitTime还为获取到则返回false
     * @param lockKey
     * @param waitTime
     * @param unit
     * @return
     * @throws Exception
     */
    boolean tryLock(String lockKey,long waitTime,long leaseTime,TimeUnit unit) throws Exception;

    /**
     * 锁是否被任意一个线程锁持有
     * @param lockKey
     * @return
     */
    boolean isLocked(String lockKey);

    /**
     * isHeldByCurrentThread()的作用是查询当前线程是否保持此锁定
     * @param lockKey
     * @return
     */
    boolean isHeldByCurrentThread(String lockKey);
}
