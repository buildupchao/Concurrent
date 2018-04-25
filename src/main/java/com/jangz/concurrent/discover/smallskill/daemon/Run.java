package com.jangz.concurrent.discover.smallskill.daemon;

public class Run {
	
	public static void main(String[] args) throws InterruptedException {
		MyThread t = new MyThread();
		t.setDaemon(true);
		t.start();
		Thread.sleep(5000);
		System.out.println("Exit to main");
	}
}
