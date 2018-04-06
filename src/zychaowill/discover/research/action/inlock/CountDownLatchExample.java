package zychaowill.discover.research.action.inlock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample implements Runnable {

	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchExample example = new CountDownLatchExample();
	
	@Override
	public void run() {
		try {
			Thread.sleep(new Random().nextInt(10) * 1000);
			System.out.println("check complete");
			end.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++)
			executors.submit(example);
		end.await();
		System.out.println("Fire!");
		executors.shutdown();
	}
}
