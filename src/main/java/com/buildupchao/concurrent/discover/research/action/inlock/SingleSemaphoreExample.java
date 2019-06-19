package com.buildupchao.concurrent.discover.research.action.inlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SingleSemaphoreExample implements Runnable {

	final Semaphore semaphore = new Semaphore(1);
	
	@Override
	public void run() {
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getId() + " takes fork, then eats, last to put down fork.");
			Thread.sleep(1200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	public static void main(String[] args) {
		ExecutorService executors = Executors.newFixedThreadPool(10);
		SingleSemaphoreExample example = new SingleSemaphoreExample();
		for (int i = 0; i < 10; i++)
			executors.submit(example);
		executors.shutdown();
	}
}
