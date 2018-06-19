package com.jangz.concurrent.discover.research.action.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RejectThreadPoolExample {
	public static class MyTask implements Runnable {
		@Override
		public void run() {
			System.out.println(System.currentTimeMillis() + ":Thread ID:" + Thread.currentThread().getId());
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		MyTask task = new MyTask();
		ExecutorService executors = new ThreadPoolExecutor(5, 5, 
				0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(10),
				Executors.defaultThreadFactory(),
				new RejectedExecutionHandler() {
					
					@Override
					public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
						System.out.println(r.toString() + " is discard");
					}
				});
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			executors.submit(task);
			Thread.sleep(10);
		}
		executors.shutdown();
	}
}
