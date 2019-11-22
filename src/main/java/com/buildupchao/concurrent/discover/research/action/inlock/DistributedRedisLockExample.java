package com.buildupchao.concurrent.discover.research.action.inlock;

import com.google.common.collect.Queues;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author buildupchao
 * @date 2019/11/22 15:12
 * @since JDK1.8
 **/
public class DistributedRedisLockExample {

    private static JedisPool pool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(200);
        // 设置最大空闲数
        config.setMaxIdle(8);
        // 设置最大等待时间
        config.setMaxWaitMillis(1000 * 100);
        // 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
        config.setTestOnBorrow(true);
        pool = new JedisPool(config, "localhost", 6379, false);
    }

    private DistributedRedisLock distributedRedisLock = new DistributedRedisLock(pool);

    public void fire() {
        // 1.获取redis锁
        String identifierValue = distributedRedisLock.tryGetDistributedLock(5000L, 5000L);

        if (identifierValue == null) {
            System.out.println(Thread.currentThread().getName() + " get lock failed, because of timeout. ");
            return;
        }
        System.out.println(Thread.currentThread().getName() + " get lock successfully, lock id: " + identifierValue + ", do business.");

        // 2.释放锁
        distributedRedisLock.releaseDistributedLock(identifierValue);
        System.out.println(Thread.currentThread().getName() + " release lock id: " + identifierValue);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                50,
                50,
                0L,
                TimeUnit.SECONDS,
                Queues.newArrayBlockingQueue(100),
                new ThreadPoolExecutor.AbortPolicy()
        );
        DistributedRedisLockExample distributedRedisLockExample = new DistributedRedisLockExample();
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> distributedRedisLockExample.fire());
        }
    }
}
