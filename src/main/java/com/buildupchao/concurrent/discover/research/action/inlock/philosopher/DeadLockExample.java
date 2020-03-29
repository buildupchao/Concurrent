package com.buildupchao.concurrent.discover.research.action.inlock.philosopher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author buildupchao
 * @date 2018/07/11
 * @since JDK1.8
 */
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
