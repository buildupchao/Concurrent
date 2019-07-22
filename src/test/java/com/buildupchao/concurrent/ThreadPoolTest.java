package com.buildupchao.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author buildupchao
 * @date 2019/07/19 15:38
 * @since JDK 1.8
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		for (int i = 0; i < 100; i++) {
			executor.execute(new MyRunner(i));
			System.out.println();
		}
//		executor.shutdown();
		executor.shutdownNow();
		System.out.println("DONE");
	}
	
	static class MyRunner implements Runnable {

		private int i;
		
		public MyRunner(int i) {
			super();
			this.i = i;
		}

		/* 
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println("thread-" + i + " finished!");
			} catch (InterruptedException e) {
				System.out.println("thread-" + i + " error : " + e.getMessage());
			}
		}
		
	}
}