package com.jangz.concurrent.thread.basic;

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
				System.out.println(Thread.currentThread().getName() + "�õ���");
				System.out.println(Thread.currentThread().getName() + "�ȴ��ź�");
				
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + "�õ��ź�");
				reentrantLock.unlock();
			}
		}, "�߳�1").start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				reentrantLock.lock();
				System.out.println(Thread.currentThread().getName() + "�õ���");
				
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println(Thread.currentThread().getName() + "�����ź�");
				condition.signalAll();
				reentrantLock.unlock();
			}
		}, "�߳�2").start();
	}
	
}
