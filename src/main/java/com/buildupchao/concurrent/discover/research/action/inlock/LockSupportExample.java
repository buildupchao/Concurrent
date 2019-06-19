package com.buildupchao.concurrent.discover.research.action.inlock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {
	public static byte[] LOCK = new byte[0];
	static ChangeObjectThread t1 = new ChangeObjectThread("t1");
	static ChangeObjectThread t2 = new ChangeObjectThread("t2");
	
	public static class ChangeObjectThread extends Thread {
		public ChangeObjectThread(String name) {
			super.setName(name);
		}
		
		@Override
		public void run() {
			synchronized (LOCK) {
				System.out.println("in " + getName());
				LockSupport.park();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		t1.start();
		Thread.sleep(100);
		t2.start();
		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
		t1.join();
		t2.join();
	}
}
