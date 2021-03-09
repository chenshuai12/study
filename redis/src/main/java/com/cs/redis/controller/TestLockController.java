package com.cs.redis.controller;


import com.cs.redis.common.RedissonLockAnnotation;
import com.cs.redis.service.ZookeeperOpera;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestLockController {
    @Autowired
    private ZookeeperOpera zookeeperOpera;
    public static final int THREAD_SLEEP_TIME = 5000;
    /**
     * 测试接口
     * @return 返回值
     */

    @GetMapping(value = "testLock", consumes = "application/json")
    @RedissonLockAnnotation(lockRedisKey = "the-only-id")
    public String testLock() {
        /**
         * 请求总携带一个唯一的id 谁拿到谁执行，非常的好理解
         */
        try {
            Thread.sleep(THREAD_SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("执行相关业务...");
        log.info("业务执行中.....");
        log.info("业务执行结束.....");

        return "success";
    }

    @GetMapping(value = "testZooLock")
    public String testZooLock(@RequestParam("seckill_id") long seckillId, @RequestParam("user_id") long userId) {
        zookeeperOpera.testLock(seckillId, userId);

        return "hello world!";
    }
}
