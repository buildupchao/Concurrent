package com.buildupchao.concurrent.discover.research.action.pool;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class CustomThreadPoolExample {

    public static void main(String[] args) {
        CustomThreadPool executors = CustomThreadPool.getThreadPool();
        executors.execute(new Runnable[] {
            new Task(), new Task(), new Task()
        });
        executors.execute(new Runnable[] {
                new Task(), new Task(), new Task()
        });
        System.out.println(executors);

        executors.destroy();
        System.out.println(executors);
    }

    static class Task implements Runnable {

        private static volatile int i = 1;

        @Override
        public void run() {
            System.out.printf("Task %d finished.\n", (i++));
        }
    }
}
