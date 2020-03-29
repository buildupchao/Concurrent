package com.buildupchao.concurrent.discover.research.simplify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
@Slf4j
public class ProducerAndConsumer {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private boolean hasValue = false;

	public void produce() {
		try {
			lock.lock();
			while (hasValue) {
				condition.await();
			}
			log.info("[produce] Producer produces a product.");
			hasValue = true;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void consume() {
		try {
			lock.lock();
			while (!hasValue) {
				condition.await();
			}
			log.info("[consume] Consumer consumes a product.");
			hasValue = false;
			condition.signalAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
