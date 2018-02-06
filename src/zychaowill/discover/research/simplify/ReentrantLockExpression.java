package zychaowill.discover.research.simplify;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReentrantLockExpression {

	private Lock lock = new ReentrantLock();

	public void tryReentrantLock() {
		try {
			lock.lock();
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
		try {
			lock.lock();
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
