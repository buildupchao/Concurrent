package com.buildupchao.concurrent.discover.smallskill.basic;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
	public static void main(String[] args) {
		final Function f = new Function();
		new Thread(() -> {
			for (int i = 0; i < 50; i++) {
				f.sub();
			}
		}).start();
		
		for (int i = 0; i < 50; i++) {
			f.main();
		}
	}
}

class Function {
	private boolean flag = true;
	
	Lock lock = new ReentrantLock();
	Condition condition = lock.newCondition();
	
	public void sub() {
		lock.lock();
		try {
			while (flag) {
				try {
					condition.await();
				} catch(InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
			for (int i = 0; i < 10; i++) {
				System.out.println("sub: " + i);
			}
			
			flag = true;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void main() {
		lock.lock();
		try {
			while (!flag) {
				try {
					condition.await();
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
			for (int i = 0; i < 20; i++) {
				System.out.println("main: " + i);
			}
			flag = false;
			condition.signal();
		} finally {
			lock.unlock();
		}
	}
}
