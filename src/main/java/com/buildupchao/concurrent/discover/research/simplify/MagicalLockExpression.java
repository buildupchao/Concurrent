package com.buildupchao.concurrent.discover.research.simplify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
@Getter
@Slf4j
public class MagicalLockExpression {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void serviceHoldCount() {
		lock.lock();
		try {
			log.info("[serviceHoldCount] holdCount is {}.", lock.getHoldCount());
			serviceHoldCountAgain();
		} finally {
			lock.unlock();
		}
	}

	private void serviceHoldCountAgain() {
		lock.lock();
		try {
			log.info("[serviceHoldCountAgain] holdCount is {}.", lock.getHoldCount());
		} finally {
			lock.unlock();
		}
	}

	public void serviceQueueLengh() {
		lock.lock();
		try {
			log.info("[serviceQueueLengh] {} enters.", Thread.currentThread().getName());
			log.info("[showQueueLength] There are {} threads waiting for holding lock.", lock.getQueueLength());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void serviceWait() {
		lock.lock();
		try {
			log.info("[serviceWait] Thread {} enters and waits.", Thread.currentThread().getName());
			condition.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			log.info("[serviceWait] Thread {} release lock.", Thread.currentThread().getName());
		}
	}

	public void serviceNotify() {
		lock.lock();
		try {
			log.info("[serviceNotify] There are {} threads waiting for condition.", lock.getWaitQueueLength(condition));
			condition.signalAll();
			log.info("[serviceNotify] Fire signal!");
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
