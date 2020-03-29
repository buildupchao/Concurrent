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
public class ReentrantLockExpression {

	private Lock lock = new ReentrantLock();

	public void tryReentrantLock() {
		lock.lock();
		try {
			System.out.printf("[tryReentrantLock] begin to execute, name is %s, time is %d.\n", Thread.currentThread().getName(), System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.printf("[tryReentrantLock] end to execute, name is %s, time is %d.\n", Thread.currentThread().getName(), System.currentTimeMillis());
		} catch (InterruptedException e) {
			log.error("Exception is from tryReentrantLock method.", e);
		} finally {
			lock.unlock();
		}
	}

	public void tryReentrantLockAgain() {
		lock.lock();
		try {
			System.out.printf("[tryReentrantLockAgain] begin to execute, name is %s, time is %d.\n", Thread.currentThread().getName(), System.currentTimeMillis());
			Thread.sleep(5000);
			System.out.printf("[tryReentrantLockAgain] end to execute, name is %s, time is %d.\n", Thread.currentThread().getName(), System.currentTimeMillis());
		} catch (InterruptedException e) {
			log.error("Exception is from tryReentrantLockAgain method.", e);
		} finally {
			lock.unlock();
		}
	}
}
