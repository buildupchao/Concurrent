package com.jangz.concurrent.discover.research.action.inlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchAdvancedExample {

	public static void main(String[] args) {
		testExamples();
	}

	private static void testExamples() {
		try {
			ExecutorService executors = Executors.newFixedThreadPool(2);
			CountDownLatch latch = new CountDownLatch(1);
			executors.execute(new TestExample(latch));
			latch.await(5, TimeUnit.SECONDS);
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
				Thread.sleep(5000);
				System.out.println("WAITING FINISHED_______");
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}
}
