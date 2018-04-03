package zychaowill.discover.research.action.security.errors;

import java.util.Vector;

public class VectorBasedOnConcurrentExample {
	static Vector<Integer> vector = new Vector<>(10);
	
	public static class AddThread implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 1_000_000; i++)
				vector.add(i);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(vector.size());
	}
}
