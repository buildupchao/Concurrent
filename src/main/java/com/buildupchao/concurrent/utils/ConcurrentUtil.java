package com.buildupchao.concurrent.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *     并发工具类
 * </p>
 * @author buildupchao
 * @date 2019/11/28 05:24
 * @since JDK 1.8
 */
public class ConcurrentUtil {

    /**
     * 真正并发工具(可保证同一时刻同时执行代码段)
     * @param parallism
     * @param concurrentRunnable
     * @throws InterruptedException
     */
    public static void latch(int parallism, ConcurrentRunnable concurrentRunnable) throws InterruptedException {
        CountDownLatch start = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(parallism);
        ExecutorService executorService = Executors.newFixedThreadPool(parallism);

        for (int i = 0; i < parallism; i++) {
            Runnable runnable = () -> {
              try {
                  start.await();
                  concurrentRunnable.run();
              } catch (InterruptedException ex) {
                  ex.printStackTrace();
              } finally {
                  end.countDown();
              }
            };
            executorService.submit(runnable);
        }

        start.countDown();
        end.await();
        executorService.shutdown();
    }

    @FunctionalInterface
    public interface ConcurrentRunnable {
        /**
         * 需并发执行的代码
         */
        void run();
    }
}
