package com.cs.redis.service.impl;

import com.cs.redis.service.ZookeeperOpera;
import com.cs.redis.util.ZkLockUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ZookeeperOperaImpl implements ZookeeperOpera {
    private static final long THREAD_SLEEP_TIME = 5000;

    @Override
    public void testLock(long seckillId,long userId) {
        boolean res = false;
        try {
            res =ZkLockUtil.acquire(1, TimeUnit.SECONDS);
            if (res){
                try {
                    Thread.sleep(THREAD_SLEEP_TIME);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("获取锁成功，开始执行任务，睡眠5秒");
            }else {
                log.info("获取锁失败，不能执行任务。抛出异常");
                throw new Exception("当前人数较多，请稍后再试");
            }
        } catch (Exception e) {
            log.error("获取锁抛出异常，请检查,{}",e);
        }finally {
            if (res){
                //释放锁
                ZkLockUtil.release();
                log.info("释放锁成功");
            }
        }
    }
}
