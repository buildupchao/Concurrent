package com.buildupchao.concurrent.discover.research.action.pool.threadpool;

/**
 * @author buildupchao
 * @date 2019/9/14 22:23
 * @since JDK 1.8
 */
public interface SimpleThreadPool<Job extends Runnable> {

    /**
     * 执行一个Job，这个Job需要实现Runnable
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作者线程
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作者线程
     * @param num
     */
    void removeWorkers(int num);

    /**
     * 获取正在等待执行的任务数量
     * @return
     */
    int getJobSize();
}
