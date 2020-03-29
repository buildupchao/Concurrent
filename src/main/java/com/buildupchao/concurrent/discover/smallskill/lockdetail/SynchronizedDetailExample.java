package com.buildupchao.concurrent.discover.smallskill.lockdetail;

/**
 * <p>
 *     javap -v SynchronizedDetailExample
 * </p>
 *
 * @author buildupchao
 * @date 2020/03/29 16:58
 * @since JDK 1.8
 */
public class SynchronizedDetailExample {

    public static void main(String[] args) {
        // 对 SynchronizedDetailExample Class 对象进行加锁
        synchronized (SynchronizedDetailExample.class) {}

        // 静态同步方法，对 Synchronized Class 对象进行加锁
        m();
    }

    public static synchronized void m() {}
}
