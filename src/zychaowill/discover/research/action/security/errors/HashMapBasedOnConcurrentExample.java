package zychaowill.discover.research.action.security.errors;

import java.util.HashMap;

public class HashMapBasedOnConcurrentExample {
	static HashMap<String, String> map = new HashMap<>();

	public static class AddThread implements Runnable {
		int start = 0;

		public AddThread(int start) {
			super();
			this.start = start;
		}

		@Override
		public void run() {
			for (int i = start; i < 100_000; i++)
				map.put(Integer.toString(i), Integer.toBinaryString(i));
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread(0));
		Thread t2 = new Thread(new AddThread(1));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(map.size());
	}
}
