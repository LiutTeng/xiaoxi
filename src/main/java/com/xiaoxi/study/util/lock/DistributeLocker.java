package com.xiaoxi.study.util.lock;


import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author liuteng
 */
@Slf4j
@Component
public class DistributeLocker {

    private RedissonClient redissonClient;

    public DistributeLocker(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public void lock (String key, long exprieTime, LockTask task) {
        lock(0L, key, exprieTime, task);
    }

    public void lock (long waitTime, String key, long exprieTime, LockTask task) {
        RLock rLock = redissonClient.getLock(key);
        try {
            if (rLock.tryLock(waitTime, exprieTime, TimeUnit.MILLISECONDS)) {
                task.success();
            } else {
                task.failure();
            }
        } catch (Exception e) {
            log.error("DistributeLocker.lock Error. key:{}, e:{}", key, e);
        } finally {
            rLock.unlock();
        }
    }

}
