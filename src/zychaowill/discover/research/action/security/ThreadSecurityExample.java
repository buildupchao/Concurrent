package zychaowill.discover.research.action.security;

public class ThreadSecurityExample implements Runnable {
	static ThreadSecurityExample instance = new ThreadSecurityExample();
	static int i = 0;
	
	public synchronized void increase() {
		i++;
	}

	@Override
	public void run() {
		for (int j = 0; j < 10_000_000; j++)
			increase();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
