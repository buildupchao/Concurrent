package com.buildupchao.concurrent.discover.custom.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

	private final Sync sync;
	
	public MyLock() {
		sync = new Sync();
	}

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}

	@Override
	public boolean tryLock() {
		return sync.tryAcquire(1);
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}
	
	static class Sync extends AbstractQueuedSynchronizer {
		private static final long serialVersionUID = 6102273050063936242L;

		@Override
		protected boolean isHeldExclusively() {
			return Thread.currentThread() == getExclusiveOwnerThread();
		}

		@Override
		protected boolean tryAcquire(int arg) {
			Thread currentThread = Thread.currentThread();
			int state = getState();
			if (state == 0) {
				if (hasQueuedPredecessors()) {
					return false;
				}
				boolean flag = compareAndSetState(1, 4);
				setExclusiveOwnerThread(currentThread);
				return true;
			}
			if (isHeldExclusively()) {
				setState(state + arg);
				return true;
			}
			return false;
		}

		@Override
		protected boolean tryRelease(int arg) {
			int newState = getState() - arg;
			setState(newState < 0 ? 0 : newState);
			if (newState == 0) {
				setExclusiveOwnerThread(null);
				return true;
			}
			return false;
		}
	}
}
