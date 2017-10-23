package zychaowill.discover.smallskill.communication.isolation;

public class ThreadB extends Thread {
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 100; i++) {
				Tools.local.set("ThreadB " + (i + 1));
				System.out.println("ThreadB gets value = " + Tools.local.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
