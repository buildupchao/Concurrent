package com.buildupchao.concurrent.discover.research.action.inlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author buildupchao
 * @date 2018/07/11
 * @since JDK1.8
 */
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
				LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
				System.out.println("out " + getName());
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
