package com.buildupchao.concurrent.discover.smallskill.communication.base;

public class ThreadA extends Thread {

	private Object lock;

	public ThreadA(Object lock) {
		super();
		this.lock = lock;
	}
	
	@Override
	public void run() {
		try {
			synchronized (lock) {
				if (MyList.size() != 5) {
					System.out.println(Thread.currentThread().getName() + " wait begin " + System.currentTimeMillis());
					lock.wait();
					System.out.println(Thread.currentThread().getName() + " wait end " + System.currentTimeMillis());
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
