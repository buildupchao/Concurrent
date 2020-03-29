package com.buildupchao.concurrent.discover.research.simplify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
@Slf4j
public class ReentrantLockCondition {

	private Lock lock = new ReentrantLock();
	private Condition conditionA = lock.newCondition();
	private Condition conditionB = lock.newCondition();
	
	public void await() {
		lock.lock();
		try {
			log.info("[await] start to execute...");
			conditionA.await();
			log.info("[await] end to execute...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			log.info("[await] release lock.");
		}
	}
	
	public void awaitAgain() {
		lock.lock();
		try {
			log.info("[awaitAgain] start to execute...");
			conditionB.await();
			log.info("[awaitAgain] end to execute...");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
			log.info("[awaitAgain] release lock.");
		}
	}

	public void signal() {
		lock.lock();
		try {
			log.info("[signal] is coming! Current time is {}.", System.currentTimeMillis());
			conditionA.signalAll();
		} finally {
			lock.unlock();
		}
	}
	
	public void signalAgain() {
		lock.lock();
		try {
			log.info("[signalAgain] is coming! Current time is {}.", System.currentTimeMillis());
			conditionB.signalAll();
		} finally {
			lock.unlock();
		}
	}
}
