package com.buildupchao.concurrent.discover.research.action.inlock.philosopher;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author buildupchao
 * @date 2018/07/11
 * @since JDK1.8
 */
public class Chopstick {
	private int index;
	private boolean use = false;
	
	private Lock lock = new ReentrantLock();

	public Chopstick(int index) {
		super();
		this.index = index;
	}

	@Override
	public String toString() {
		return "{\"index\":\"" + index + "\", \"use\":\"" + use + "\"}";
	}
	
	public void take() throws InterruptedException {
		lock.lock();
		try {
			while (use) {
				wait();
			}
			use = true;
		} finally {
			lock.unlock();
		}
	}
	
	public void drop() {
		lock.lock();
		try {
			use = false;
			notifyAll();
		} finally {
			lock.unlock();
		}
	}
}
