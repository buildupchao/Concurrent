package zychaowill.discover.smallskill.communication.pc.backup;

public class DBTools {

	private volatile boolean preIsA = false;

	public synchronized void backupA() {
		try {
			while (preIsA) {
				wait();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println("★★★★★");
			}
			preIsA = true;
			notifyAll();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public synchronized void backupB() {
		try {
			while (!preIsA) {
				wait();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println("☆☆☆☆☆");
			}
			preIsA = false;
			notifyAll();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
