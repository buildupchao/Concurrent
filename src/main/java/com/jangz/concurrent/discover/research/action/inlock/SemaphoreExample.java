package com.jangz.concurrent.discover.research.action.inlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreExample implements Runnable {

	final Semaphore semaphore = new Semaphore(5);
	
	@Override
	public void run() {
		try {
			semaphore.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId() + " done!");
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(20);
		final SemaphoreExample example = new SemaphoreExample();
		for (int i = 0; i < 20; i++)
			executors.submit(example);
		executors.shutdown();
	}
}
