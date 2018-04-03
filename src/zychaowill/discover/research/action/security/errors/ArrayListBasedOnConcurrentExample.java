package zychaowill.discover.research.action.security.errors;

import java.util.ArrayList;

public class ArrayListBasedOnConcurrentExample {
	static ArrayList<Integer> list = new ArrayList<>(10);
	
	public static class AddThread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 1_000_000; i++)
				list.add(i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(list.size());
	}
}