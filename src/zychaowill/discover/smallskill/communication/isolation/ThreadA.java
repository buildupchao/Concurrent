package zychaowill.discover.smallskill.communication.isolation;

public class ThreadA extends Thread {
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				Tools.local.set("ThreadA " + (i + 1));
				System.out.println("ThreadA gets value " + Tools.local.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
