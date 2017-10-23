package zychaowill.discover.smallskill.communication;

public class Run {
	public static void main(String[] args) {
//		try {
			Object lock = new Object();
			ThreadA a = new ThreadA(lock);
			a.setName("A");
			a.start();
//			Thread.sleep(50);
			ThreadB b = new ThreadB(lock);
			b.setName("B");
			b.start();
			ThreadA c = new ThreadA(lock);
			c.setName("C");
			c.start();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
}
