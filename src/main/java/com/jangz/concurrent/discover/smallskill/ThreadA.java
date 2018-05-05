package com.jangz.concurrent.discover.smallskill;

public class ThreadA {
	
	public static void main(String[] args) {
		ThreadB b = new ThreadB();
		b.start();
		System.out.println("b is starting...");
		
		synchronized (b) {
			try {
				System.out.println("Waiting for b to complete...");
				b.wait();
			} catch (InterruptedException ex) {
				
			}
			System.out.println("Total is: " + b.total);
		}
	}
}

class ThreadB extends Thread {
	
	int total;
	
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized (this) {
			System.out.println("ThreadB is running...");
			for (int i = 0; i < 100; i++) {
				total += i;
				System.out.println("total is " + total);
			}
			System.out.println("Completed. Now back to main thread");
			notify();
		}
	}
}
