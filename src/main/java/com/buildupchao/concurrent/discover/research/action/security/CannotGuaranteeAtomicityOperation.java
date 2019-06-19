package com.buildupchao.concurrent.discover.research.action.security;

/**
 * In theoretically, the final result should be 100,000. The output is always less than 100,000.
 *
 * @author jangz
 * @since
 */
public class CannotGuaranteeAtomicityOperation {
	
	volatile static int i = 0;
	
	public static class PlusTask implements Runnable {
		@Override
		public void run() {
			for (int k = 0; k < 10_000; k++)
//				synchronized (CannotGuaranteeAtomicityOperation.class) {
					i++;
//				}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(new PlusTask());
			threads[i].start();
		}
		for (int i = 0; i < 10; i++)
			threads[i].join();
		System.out.println(i);
	}
}
