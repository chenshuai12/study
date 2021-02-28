package com.cs.redis.Aop;

import com.cs.redis.common.DistributeLocker;
import com.cs.redis.common.RedissonLockAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 分布式锁的aop
 * 无论是否抛出异常，也无论什么地方return返回，finally语句块总是会执行，这样你有机会调用close来关闭数据库连接（即使未打开或打开失败，关闭操作永远是可以执行的），以便于释放已经产生的连接，释放资源
 * 顺便说明，return是可以放在try语句块中的，但不管在什么时机返回，在返回前，finally将会执行
 *
 *Case2：
 *  *  至少有两种情况下finally语句是不会被执行的：
 *  *  （1）try语句没有被执行到，如在try语句之前return就返回了，这样finally语句就不会执行。这也说明了finally语句被执行的必要而非充分条件是：相应的try语句一定被执行到。
 *  *  （2）在try块|catch块中有System.exit(0);这样的语句。System.exit(0)是终止Java虚拟机JVM的，连JVM都停止了，所有都结束了，当然finally语句也不会被执行到。
 *  *
 *  *  在try-catch-finally中, 当return遇到finally，return对finally无效，即:
 *  *
 *  *       1.在try catch块里return的时候，finally也会被执行。
 *  *
 *  *       2.finally里的return语句会把try catch块里的return语句效果给覆盖掉。
 *  *
 *  *  结论：return语句并不一定都是函数的出口，执行return时，只是把return后面的值复制了一份到返回值变量里去了。
 */
@Aspect
@Component
@Slf4j
public class RedissonLockAop {
    public static final int WAIT_GET_LOCK_TIME = 10;
    public static final int WAIT_RELEASE_LOCK_TIME = 5000;

    @Autowired
    private DistributeLocker locker;

    /**
     * 切点，拦截被@RedissonLockAnnotation修饰的方法
     */
    @Pointcut("@annotation(com.cs.redis.common.RedissonLockAnnotation)")
    public void redissonLockPoint(){

    }

    /**
     * joinPoint 代表当前正在运行的方法
     * @param joinPoint
     * @return
     * @throws InterruptedException
     */
    @Around("redissonLockPoint()")
    @ResponseBody
    public String checkLock(ProceedingJoinPoint joinPoint) throws Exception {
        // 当前线程名
        String threadName = Thread.currentThread().getName();
        log.info("线程{}--------进入分布式锁aop-----",threadName);
        //获取该注解的实例对象
        RedissonLockAnnotation annotation = ((MethodSignature)joinPoint.getSignature()).getMethod().getAnnotation(RedissonLockAnnotation.class);
        //生成分布式锁key的键名，以逗号分割
        String lockRedisKey = annotation.lockRedisKey();
        log.info("存在于注解中的key值是：{}",lockRedisKey);

        //获取存在于请求头中的唯一id值
        String lockRedisValue = ((ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getHeader(lockRedisKey);

        if (StringUtils.isBlank(lockRedisKey)){
            log.info("线程{} lockRedisKey设置为空，不加锁",threadName);
            try {
                joinPoint.proceed();
            }catch (Throwable throwable){
                throwable.printStackTrace();
                log.error("process method failed...now print message:{}",throwable);
            }
            throw new Exception("NULL LOCK");
        }else {
            log.info("线程{}锁的value值是:{}",threadName,lockRedisValue);
            //获取锁 3000 等到获取所得时间 leaseTime 获取锁后持有时间 时间单位MILLISECONDS：毫秒
            if (locker.tryLock(lockRedisValue,WAIT_GET_LOCK_TIME,WAIT_RELEASE_LOCK_TIME, TimeUnit.MILLISECONDS)){
                try {
                    log.info("线程{}获取锁成功",threadName);
                    return (String) joinPoint.proceed();
                }catch (Throwable throwable){
                    throwable.printStackTrace();
                    log.info("process method failed...now print message:{}",throwable);
                }finally {
                    if (locker.isLocked(lockRedisValue)){
                        log.info("key={}对应的锁被持有,线程{}",lockRedisValue, threadName);
                        if (locker.isHeldByCurrentThread(lockRedisValue)) {
                            log.info("当前线程 {} 保持锁定", threadName);
                            locker.unlock(lockRedisValue);
                            log.info("线程{} 释放锁", threadName);
                        }
                    }
                }
            }else {
                log.info("线程{}获取锁失败",threadName);
                throw new Exception("GET LOCK FAIL");
            }
        }

        return null;
    }
}
