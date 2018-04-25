package com.jangz.concurrent.thread.basic.simple;

public class SyncDubbo {

	public synchronized void method1() {
		System.out.println("method1..");
		method2();
	}

	public synchronized void method2() {
		System.out.println("method2..");
		method3();
	}

	public synchronized void method3() {
		System.out.println("method3..");
	}

	public static void main(String[] args) {
		final SyncDubbo sd = new SyncDubbo();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				sd.method1();
			}
		});
		t1.start();
	}
}
