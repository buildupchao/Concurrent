package com.jangz.concurrent.discover.research.action.inlock.philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLockExample {
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newCachedThreadPool();
		int size = 5;
		Chopstick[] chopstick = new Chopstick[size];
		for (int i = 0; i < size; i++) {
			chopstick[i] = new Chopstick(i);
		}

		int thinkingTime = 0;
		for (int i = 0; i < size; i++) {
			executors.execute(new Philosopher(chopstick[i], chopstick[(i + 1) % size], i, thinkingTime));
		}
		Thread.sleep(4 * 1000);
		executors.shutdown();
	}
}
