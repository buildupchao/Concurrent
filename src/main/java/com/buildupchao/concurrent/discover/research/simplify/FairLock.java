package com.buildupchao.concurrent.discover.research.simplify;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
@Slf4j
public class FairLock {

	private Lock lock;
	
	public FairLock(boolean fair) {
		lock = new ReentrantLock(fair);
	}
	
	public void tryFairLock() {
		try {
			lock.lock();
			log.info("[tryFairLock] Thread name is {}, hold lock!", Thread.currentThread().getName());
		} finally {
			log.info("[tryFairLock] Thread name is {}, release lock!", Thread.currentThread().getName());
			lock.unlock();
		}
	}
}
