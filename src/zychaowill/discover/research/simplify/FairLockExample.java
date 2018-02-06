package zychaowill.discover.research.simplify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FairLockExample {

	public static void main(String[] args) {
//		fairLock();
		notFairLock();
	}
	
	static void fairLock() {
		final FairLock service = new FairLock(true);
		Runnable target = () -> {
			log.info("[fairLock] Thread {} running.", Thread.currentThread().getName());
			service.tryFairLock();
		};
		
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++)
			threads[i] = new Thread(target);
		for (int i = 0; i < 10; i++)
			threads[i].start();
	}
	
	static void notFairLock() {
		final FairLock service = new FairLock(false);
		Runnable target = () -> {
			log.info("[fairLock] Thread {} running.", Thread.currentThread().getName());
			service.tryFairLock();
		};
		
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++)
			threads[i] = new Thread(target);
		for (int i = 0; i < 10; i++)
			threads[i].start();
	}
}
