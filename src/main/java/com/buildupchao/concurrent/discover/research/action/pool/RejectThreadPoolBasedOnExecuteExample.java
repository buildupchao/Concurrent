package com.buildupchao.concurrent.discover.research.action.pool;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class RejectThreadPoolBasedOnExecuteExample {
	@Data
	@Getter
	@Setter
	public static class MyTask implements Runnable {
		
		private String taskInfo;
		private Integer taskId;
		private List<String> taskCodes;
		private CountDownLatch latch;
		
		public MyTask(String taskInfo, Integer taskId, List<String> taskCodes, CountDownLatch latch) {
			super();
			this.taskInfo = taskInfo;
			this.taskId = taskId;
			this.taskCodes = taskCodes;
			this.latch = latch;
		}

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId() + ", taskId:" + taskId);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch latch = new CountDownLatch(10);
		ExecutorService executors = new ThreadPoolExecutor(5, 5, 
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(2),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						if (r instanceof MyTask) {
							System.out.println(((MyTask) r).getTaskId() + " is discard");
							// you can re-do this task by anyway you like
						} else {
							System.out.println("no handle");
						}
					}
				});
		
		for (int i = 0; i < 10; i++) {
			// 此处需要注意，如果通过submit提交任务，那么拒绝策略里面是无法通过Runnable获取到对象属性信息的!!!
			executors.execute(new MyTask("myself", i, Lists.newArrayList("test1", "test2"), latch));
			Thread.sleep(10);
		}
		executors.shutdown();
	}
}
