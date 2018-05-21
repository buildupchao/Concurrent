package com.jangz.concurrent.discover.research.action.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yachao on 18/5/20.
 */
@Slf4j
public final class CustomThreadPool {

    private static int workerNum = 5;

    private WorkThread [] workThreads;

    private static volatile int finishedTask = 0;

    private List<Runnable> taskQueue = new LinkedList<>();
    private static CustomThreadPool threadPool;

    private CustomThreadPool() {
    }

    private CustomThreadPool(int workerNum) {
        CustomThreadPool.workerNum = workerNum;
        workThreads = new WorkThread[workerNum];
        for (int i = 0; i < workerNum; i++) {
            workThreads[i] = new WorkThread();
            workThreads[i].start();
        }
    }

    public static CustomThreadPool getThreadPool() {
        return getThreadPool(CustomThreadPool.workerNum);
    }

    public static CustomThreadPool getThreadPool(int workerNum) {
        if (workerNum == 0) {
            workerNum = CustomThreadPool.workerNum;
        }
        if (threadPool == null) {
            threadPool = new CustomThreadPool(workerNum);
        }
        return threadPool;
    }

    public void execute(Runnable task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public void execute(Runnable[] tasks) {
        synchronized (taskQueue) {
            for (Runnable task : tasks) {
                taskQueue.add(task);
            }
            taskQueue.notify();
        }
    }

    public void destroy() {
        while (!taskQueue.isEmpty()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        for (int i = 0; i < workerNum; i++) {

        }
    }

    public int getWorkerThreadNumber() {
        return workerNum;
    }

    public int getFinishedTaskNumber() {
        return finishedTask;
    }

    public int getWaitTaskNumber() {
        return taskQueue.size();
    }

    @Override
    public String toString() {
        return "WorkThread number: " + workerNum + ", finished task number: " + finishedTask
                + ", wait task number: " + getWaitTaskNumber();
    }

    private class WorkThread extends Thread  {

        private boolean isRunning = true;

        @Override
        public void run() {
            Runnable r = null;
            while (isRunning) {
                synchronized (taskQueue) {
                    while (isRunning && taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (!taskQueue.isEmpty()) {
                        r = taskQueue.remove(0);
                    }
                }
                if (r != null) {
                    try {
                        r.run();
                    } catch (Exception e) {
                        log.error("Throw an error when task executes.");
                    }
                }
                finishedTask++;
                r = null;
            }
        }

        public void stopWorker() {
            isRunning = false;
        }
    }
}
