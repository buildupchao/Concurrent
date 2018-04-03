package zychaowill.discover.research.action.inlock;

import java.util.concurrent.locks.ReentrantLock;

public class InterruptiblyLock implements Runnable {
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;

	public InterruptiblyLock(int lock) {
		super();
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			if (lock == 1) {
				lock1.lockInterruptibly();
				try {
					Thread.sleep(500);
				} catch (InterruptedException ex) {}
				lock2.lockInterruptibly();
			} else {
				lock2.lockInterruptibly();
				try {
					Thread.sleep(500);;
				} catch (InterruptedException ex) {}
				lock1.lockInterruptibly();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} finally {
			if (lock1.isHeldByCurrentThread())
				lock1.unlock();
			if (lock2.isHeldByCurrentThread())
				lock2.unlock();
			System.out.println(Thread.currentThread().getName() + ": 线程退出");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new InterruptiblyLock(1), "A");
		Thread t2 = new Thread(new InterruptiblyLock(2), "B");
		t1.start();
		t2.start();
		Thread.sleep(1000);
		t2.interrupt();
	}
}
