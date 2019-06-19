package com.buildupchao.concurrent.discover.research.action.pool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Queues;

/**
 *   线程方式执行失败转串行执行
 * @author buildupchao
 *         Date: 2019/03/18 11:57
 * @since JDK 1.8
 */
public class SerialRejectThreadPoolExample {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SerialRejectThreadPoolExample.class);
	
	private static final ThreadPoolExecutor TASK_POOL = new ThreadPoolExecutor(
			2,
			2,
			0L,
			TimeUnit.SECONDS,
			Queues.newArrayBlockingQueue(1),
			new BasicThreadFactory.Builder().namingPattern("task-thread-%d").build(),
			new SerialRejectPolicy()
	);
	
	static class SerialRejectPolicy implements RejectedExecutionHandler {

		@Override
		public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
			LOGGER.info("rejected [{}]", r.toString());
			r.run();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new  CountDownLatch(5);
		for (int i = 0; i < 8; i++) {
			final int num = i;
			Runnable r = () -> {
				try {
					System.out.println("Execute task-" + num + ", max=" + TASK_POOL.getActiveCount() + ", queue=" + TASK_POOL.getQueue().size());
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					latch.countDown();
				}
			};
			System.out.printf("prepare for [%s]\n", r.toString());
			TASK_POOL.execute(r);
		}
		latch.await();
		System.out.println("DONE");
		
		TASK_POOL.shutdown();
	}
}
