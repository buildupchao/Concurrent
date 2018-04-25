package com.jangz.concurrent.discover.research.action.inlock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class DoWorkBasedOnCountDownLatchExample {

	public static class Soldier implements Runnable {

		private String soldier;
		private final CountDownLatch latch;

		Soldier(CountDownLatch latch, String soldierName) {
			this.latch = latch;
			this.soldier = soldierName;
		}

		@Override
		public void run() {
			doWork();
			latch.countDown();
		}

		private void doWork() {
			try {
				Thread.sleep(Math.abs(new Random().nextInt() % 10_000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(soldier + ": finish work!");
		}

	}

	public static void main(String[] args) throws InterruptedException {
		final int N = 10;
		Thread[] allSoldier = new Thread[N];
		CountDownLatch latch = new CountDownLatch(N);
		
		System.out.println("Fall In!");
		for (int i = 0; i < N; i++) {
			allSoldier[i] = new Thread(new Soldier(latch, String.valueOf(i)));
			Thread.sleep(1000);
			allSoldier[i].start();
		}
		latch.await();
		System.out.println("Commander:[" + N + " soldiers finish work!]");
		
	}
}
