package com.buildupchao.concurrent.disruptor.examples;

/**
 * @author buildupchao
 * @date 2019/3/17 14:55
 * @since JDK 1.8
 */
public class FalseSharing implements Runnable {

    public static final int NUM_THREADS = 4; // change
    public static final long ITERATIONS = 500L * 1000L * 1000L;
    private final int arrayIndex;

    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    public FalseSharing(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    public static final class VolatileLong {
        public volatile long value = 0L;
//        public long p1, p2, p3, p4, p5, p6; // comment out
    }

    public static void main(String[] args) throws InterruptedException {
        final long start = System.nanoTime();
        runTest();
        System.out.println("duration = " + (System.nanoTime() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
