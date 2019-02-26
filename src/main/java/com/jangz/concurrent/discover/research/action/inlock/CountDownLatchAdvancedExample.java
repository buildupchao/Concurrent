package com.jangz.concurrent.discover.research.action.inlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 	如果出现await超时等待或者执行任务线程抛异常，均会返回false; 只有当任务无超时正常调用countDown()方法，await才会返回true
 * @author buildupchao
 *         Date: 2019/02/26 15:06
 * @since JDK 1.8
 */
public class CountDownLatchAdvancedExample {

	public static void main(String[] args) {
		testExamples();
	}

	private static void testExamples() {
		try {
			ExecutorService executors = Executors.newFixedThreadPool(2);
			CountDownLatch latch = new CountDownLatch(1);
			executors.execute(new TestExample(latch));
			boolean waited = latch.await(5, TimeUnit.SECONDS);
			System.out.println("waited/>" + waited);
			System.out.println("DONE________________");
			executors.shutdown();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	static class TestExample implements Runnable {

		private CountDownLatch latch;

		public TestExample(CountDownLatch latch) {
			super();
			this.latch = latch;
		}

		@Override
		public void run() {
			try {
				System.out.println("WAITING________________");
				Thread.sleep(4000);
				System.out.println("WAITING FINISHED_______");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}
}
