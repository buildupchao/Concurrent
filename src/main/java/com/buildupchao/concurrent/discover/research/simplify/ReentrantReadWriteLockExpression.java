package com.buildupchao.concurrent.discover.research.simplify;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReentrantReadWriteLockExpression {
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	public void read() {
		try {
			lock.readLock().lock();
			log.info("[read] {} holds read-lock at {}.", Thread.currentThread().getName(), System.currentTimeMillis());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			log.info("[read] {} releases read-lock at {}.", Thread.currentThread().getName(), System.currentTimeMillis());
			lock.readLock().unlock();
		}
	}
	
	public void write() {
		try {
			lock.writeLock().lock();
			log.info("[write] {} holds write-lock at {}.", Thread.currentThread().getName(), System.currentTimeMillis());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			log.info("[write] {} releases write-lock at {}.", Thread.currentThread().getName(), System.currentTimeMillis());
			lock.writeLock().unlock();
		}
	}
}
