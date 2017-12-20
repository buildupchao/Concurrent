package zychaowill.thread.basic.simple;

public class SyncException {
	
	private int i = 0;
	
	public synchronized void operation() {
		while (true) {
			try {
				i += 1;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if (i == 20) {
					throw new RuntimeException();
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public static void main(String[] args) {
		final SyncException se = new SyncException();
		Thread t1 = new Thread(() -> {
			se.operation();
		}, "t1");
		t1.start();
	}
}
