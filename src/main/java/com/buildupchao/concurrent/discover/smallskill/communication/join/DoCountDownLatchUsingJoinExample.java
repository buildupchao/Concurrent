package com.buildupchao.concurrent.discover.smallskill.communication.join;

import java.util.concurrent.TimeUnit;

/**
 * @author buildupchao
 * @date 2019-09-14 18:01
 * @since JDK 1.8
 */
public class DoCountDownLatchUsingJoinExample {

    public static void main(String[] args) throws InterruptedException {
        Thread previousThread = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程都拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回。
            Thread currentThread = new Thread(new Domino(previousThread), String.valueOf(i));
            currentThread.start();
            previousThread = currentThread;
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminates");
    }

    static class Domino implements Runnable {
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
