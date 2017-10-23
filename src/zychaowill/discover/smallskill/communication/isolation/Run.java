package zychaowill.discover.smallskill.communication.isolation;

public class Run {
	public static void main(String[] args) {
		try {
			ThreadA a = new ThreadA();
			ThreadB b = new ThreadB();
			a.start();
			b.start();
			
			for (int i = 0; i < 100; i++) {
				Tools.local.set("Main" + (i + 1));
				System.out.println("Main gets value = " + Tools.local.get());
				Thread.sleep(200);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
