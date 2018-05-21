package com.jangz.concurrent.discover.research.action.inlock;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
	public static class Soldier implements Runnable {
		private String soldier;
		private final CyclicBarrier cyclic;

		public Soldier(String soldier, CyclicBarrier cyclic) {
			super();
			this.soldier = soldier;
			this.cyclic = cyclic;
			System.out.printf("Soldier%s sign in!\n", soldier);
		}

		@Override
		public void run() {
			try {
				cyclic.await();
				doWork();
				cyclic.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}

		void doWork() {
			try {
				Thread.sleep(Math.abs(new Random().nextInt() % 10_000));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println(soldier + ": finish work!");
		}
	}

	public static class BarrierRun implements Runnable {
		boolean flag;
		int N;

		public BarrierRun(boolean flag, int n) {
			super();
			this.flag = flag;
			N = n;
		}

		@Override
		public void run() {
			if (flag)
				System.out.println("Commander:[" + N + " soldiers finish work!]");
			else {
				System.out.println("Commander:[" + N + " soldiers fall in!]");
				flag = true;
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final int N = 10;
		Thread[] allSoldier = new Thread[N];
		boolean flag = false;
		CyclicBarrier cyclic = new CyclicBarrier(N, new BarrierRun(flag, N));
		
		System.out.println("Fall In!");
		for (int i = 0; i < N; i++) {
			allSoldier[i] = new Thread(new Soldier(String.valueOf(i), cyclic));
			Thread.sleep(1000);
			allSoldier[i].start();
		}
	}
}
