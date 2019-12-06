package com.buildupchao.concurrent.discover.smallskill.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
	
	public static void main(String[] args) {
		final ReentrantLock reentrantLock = new ReentrantLock();
		final Condition condition = reentrantLock.newCondition();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				reentrantLock.lock();
				System.out.println(Thread.currentThread().getName() + "?õ???");
				System.out.println(Thread.currentThread().getName() + "??????");
				
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + "?õ????");
				reentrantLock.unlock();
			}
		}, "???1").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				reentrantLock.lock();
				System.out.println(Thread.currentThread().getName() + "?õ???");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + "???????");
				condition.signalAll();
				reentrantLock.unlock();
			}
		}, "???2").start();
	}
	
}
