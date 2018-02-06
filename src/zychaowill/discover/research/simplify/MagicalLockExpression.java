package zychaowill.discover.research.simplify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class MagicalLockExpression {

	private ReentrantLock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void serviceHoldCount() {
		try {
			lock.lock();
			log.info("[serviceHoldCount] holdCount is {}.", lock.getHoldCount());
			serviceHoldCountAgain();
		} finally {
			lock.unlock();
		}
	}

	private void serviceHoldCountAgain() {
		try {
			lock.lock();
			log.info("[serviceHoldCountAgain] holdCount is {}.", lock.getHoldCount());
		} finally {
			lock.unlock();
		}
	}

	public void serviceQueueLengh() {
		try {
			lock.lock();
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
		try {
			lock.lock();
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
		try {
			lock.lock();
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
