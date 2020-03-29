package com.buildupchao.concurrent.discover.research.simplify;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author buildupchao
 * @date 2020/03/29 10:55
 * @since JDK 1.8
 */
public class ThreadMXBeanExample {

    /**
     * [5]Monitor Ctrl-Break
     * [4]Signal Dispatcher     // 分发处理发送给 JVM 信号的线程
     * [3]Finalizer             // 调用对象 finalize 方法的线程
     * [2]Reference Handler     // 清除 Reference 的线程
     * [1]main                  // main 线程，用户程序入口
     *
     * @param args
     */
    public static void main(String[] args) {
        // 获取线程
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }
    }
}
