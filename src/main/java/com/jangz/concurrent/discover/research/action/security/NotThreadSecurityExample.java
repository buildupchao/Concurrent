package com.jangz.concurrent.discover.research.action.security;

public class NotThreadSecurityExample implements Runnable {
	static int i = 0;
	
	public static synchronized void increase() {
		i++;
	}

	@Override
	public void run() {
		for (int j = 0; j < 10_000_000; j++)
			increase();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new NotThreadSecurityExample());
		Thread t2 = new Thread(new NotThreadSecurityExample());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
