package com.buildupchao.concurrent.discover.research.action;

import java.sql.Timestamp;
import java.time.Instant;

public class WaitNotifyExample {

	final static Object LOCK = new Object();
	
	public static class T1 extends Thread {
		@Override
		public void run() {
			synchronized (LOCK) {
				System.out.println(Timestamp.from(Instant.now()) + ": T1 start!");
				
				try {
					System.out.println(Timestamp.from(Instant.now()) + ": T1 waits for LOCK!");
					LOCK.wait();
					System.out.println(Timestamp.from(Instant.now()) + ": T1 carrys on executing!");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Timestamp.from(Instant.now()) + ": T1 end!");
			}
		}
	}
	
	public static class T2 extends Thread {
		@Override
		public void run() {
			synchronized (LOCK) {
				System.out.println(Timestamp.from(Instant.now()) + ": T2 start! notify one thread");
				
				LOCK.notify();
				System.out.println(Timestamp.from(Instant.now()) + ": T2 end!");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Timestamp.from(Instant.now()) + ": T2 release lock!");
			}
		}
	}
	
	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		t1.start();
		t2.start();
	}
}
