package com.buildupchao.concurrent.discover.research.action.pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class RejectThreadPoolBasedOnExecuteExample {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RejectThreadPoolBasedOnExecuteExample.class);
	
	@Data
	@Getter
	@Setter
	public static class MyTask implements Runnable, Comparable<MyTask> {
		
		private String taskInfo;
		private Integer taskId;
		private List<String> taskCodes;
		
		public MyTask(String taskInfo, Integer taskId, List<String> taskCodes) {
			super();
			this.taskInfo = taskInfo;
			this.taskId = taskId;
			this.taskCodes = taskCodes;
		}

		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId() + ", taskId:" + taskId);
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public int compareTo(MyTask o) {

			return 0;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		TaskProducer taskProducer = new TaskProducer();
		taskProducer.setName("task-producer");
		taskProducer.start();
		
		Thread.sleep(2000);
		TaskHandler taskHandler = new TaskHandler();
		taskHandler.setName("task-handler");
		taskHandler.start();
	}

	static final ExecutorService executors = new ThreadPoolExecutor(5, 5, 
			0L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>(2),
			Executors.defaultThreadFactory(),
			new RejectedExecutionHandler() {
				
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					if (r instanceof MyTask) {
						try {
							MyTask task = (MyTask) r;
							System.out.println(task.getTaskId() + " is discard");
							// you can re-do this task by anyway you like
							// 任务被拒绝策略拦截后，放回taskQueue中进行重新排队
							pushTask(task);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					} else {
						System.out.println("no handle");
					}
				}
			});
	
	
	/**
	 * 任务处理器，从taskQueue中消费任务进行处理 
	 */
	private static class TaskHandler extends Thread {
	
		
		@Override
		public void run() {
			
			while (true) {
				try {
					MyTask task = taskQueue.take();
					
					// 此处需要注意，如果通过submit提交任务，那么拒绝策略里面是无法通过Runnable获取到对象属性信息的!!!
					executors.execute(task);
					
					//Thread.sleep(50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
	/**
	 * 任务生成器，不断生成任务且放入taskQueue中等待处理 
	 */
	private static class TaskProducer extends Thread {
		
		AtomicInteger taskIdGenerator = new AtomicInteger(0); 
		
		@Override
		public void run() {
			for (int i = 0; i < 50; i++) {
				try {
					MyTask task = new MyTask("taskInfo", taskIdGenerator.incrementAndGet(), Arrays.asList("test1", "test2"));
					pushTask(task);
					Thread.sleep(50);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	private static final PriorityBlockingQueue<MyTask> taskQueue = new PriorityBlockingQueue<>();
	
	public static void pushTask(MyTask task) throws InterruptedException {
		if (task == null) {
			return;
		}
		
		LOGGER.info("push task into queue, task={}", task.toString());
		taskQueue.put(task);
	}
}
