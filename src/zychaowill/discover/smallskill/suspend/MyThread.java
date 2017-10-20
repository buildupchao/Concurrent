package zychaowill.discover.smallskill.suspend;

public class MyThread extends Thread {
	
	private long i = 0;
	
	@Override
	public void run() {
		while (i < 20) {
			i++;
			System.out.println(i);
		}
	}
}
