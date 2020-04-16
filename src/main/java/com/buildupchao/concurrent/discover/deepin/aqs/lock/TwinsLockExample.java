package com.buildupchao.concurrent.discover.deepin.aqs.lock;

import com.buildupchao.concurrent.utils.ConcurrentUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author buildupchao
 * @date 2020/04/17 00:13
 * @since JDK 1.8
 */
public class TwinsLockExample {

    public static void main(String[] args) throws InterruptedException {
        final Lock lock = new TwinsLock();
        ConcurrentUtil.latch(10, () -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " enter");
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + " exit");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
    }
}
