package zychaowill.discover.smallskill.deadlock;

public class Run {
	public static void main(String[] args) {
		try {
			DeadThread dt = new DeadThread();
//			DeadThread2 dt = new DeadThread2();
			dt.setFlag("a");
			Thread t1 = new Thread(dt);
			t1.start();
			Thread.sleep(100);
			
			dt.setFlag("b");
			Thread t2 = new Thread(dt);
			t2.start();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
