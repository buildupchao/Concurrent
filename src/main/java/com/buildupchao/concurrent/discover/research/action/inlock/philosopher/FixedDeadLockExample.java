package com.buildupchao.concurrent.discover.research.action.inlock.philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedDeadLockExample {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newCachedThreadPool();
		int size = 5;
		Chopstick[] chopstick = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			chopstick[i] = new Chopstick(i);
		}

		int thinkingTime = 0;
		for (int i = 0; i < size - 1; i++) {
			executors.execute(new Philosopher(chopstick[i], chopstick[i + 1], i, thinkingTime));
		}
		executors.execute(new Philosopher(chopstick[0], chopstick[size - 1], size, thinkingTime));
		Thread.sleep(4 * 1000);
		executors.shutdownNow();
	}
}
