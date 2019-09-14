package com.buildupchao.concurrent.discover.research.action.pool.threadpool;

/**
 * @author buildupchao
 * @date 2019-09-14 22:55
 * @since JDK 1.8
 */
public class DefaultSimpleThreadPoolExample {

    public static void main(String[] args) {
        DefaultSimpleThreadPool pool = new DefaultSimpleThreadPool();
        for (int i = 0; i < 10; i++) {
            String value = String.valueOf(i);
            pool.execute(() -> System.out.println(value));
        }
    }
}
