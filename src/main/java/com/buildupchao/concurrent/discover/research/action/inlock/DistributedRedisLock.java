package com.buildupchao.concurrent.discover.research.action.inlock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.UUID;

/**
 * redis分布式锁
 * @author buildupchao
 * @date 2019/11/22 14:51
 * @since JDK1.8
 **/
public class DistributedRedisLock {

    /**
     * redis线程池
     */
    private JedisPool jedisPool;
    /**
     * 同时在redis上创建相同的一个key
     */
    private String redisLockKey = "redis_lock";

    public DistributedRedisLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * <p>
     *      获取redis分布式锁，有两个超时的时间问题<br/>
     *      1,在获取锁之前的超时时间：在尝试获取锁的时候，如果在规定的时间内还没有获取到锁，直接放弃<br/>
     *      2,在获取锁之后的超时时间：当获取锁成功之后，对应的key有对应有效期，对应的key在规定时间内进行失效
     * </p>
     * @param acquireTimeout
     * @param timeout
     * @return
     */
    public String tryGetDistributedLock(Long acquireTimeout, Long timeout) {
        Jedis connection = null;

        try {
            // 1.建立redis连接
            connection = jedisPool.getResource();
            // 2.定义redis对应key的value值（uuid），用于释放锁，取值为随机生成value
            String identifierValue = UUID.randomUUID().toString();

            // 3.定义获取锁成功后的锁超时时间
            int expireLock = (int) (timeout / 1000);
            // 4.定义在获取锁之前的超时时间
            Long endTime = System.currentTimeMillis() + acquireTimeout;
            // 5.使用循环机制，如果没有获取到锁，要在规定acquireTimeout时间，保证重复进行尝试获取锁（乐观锁）
            while (System.currentTimeMillis() < endTime) {
                // 获取锁
                // 6.使用setnx命令插入对应的redisLockKey，如果返回1，则表示成功获取到锁，反之失败
                if (connection.setnx(redisLockKey, identifierValue) == 1) {
                    // 设置对应key的有效期
                    connection.expire(redisLockKey, expireLock);
                    return identifierValue;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return null;
    }

    /**
     * <p>
     *     释放redis分布式锁<br/>
     *     需要对应的redisLockKey是自己创建的，然后进行connection.del(redisLockKey)删除即可<br/>
     *     补充：
     *     释放锁有两种方式：1，key自动有有效期；2，整个程序执行完毕情况下，删除对应key
     * </p>
     * @param identifierValue
     */
    public void releaseDistributedLock(String identifierValue) {
        Jedis connection = null;

        connection = jedisPool.getResource();
        try {
            // 如果该锁的id等于identifierValue，是同一把锁才可以删除
            if (connection.get(redisLockKey).equals(identifierValue)) {
                connection.del(redisLockKey);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
