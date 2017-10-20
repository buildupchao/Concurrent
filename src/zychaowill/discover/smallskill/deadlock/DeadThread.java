package zychaowill.discover.smallskill.deadlock;

public class DeadThread implements Runnable {
	
	private String username;
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	
	public void setFlag(String username) {
		this.username = username;
	}
	
	@Override
	public void run() {
		if (username.equals("a")) {
			synchronized (lock1) {
				try {
					System.out.println("username = " + username);
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
				synchronized (lock2) {
					System.out.println("按 lock1 -> lock2代码顺序执行了");
				}
			}
		}
		if (username.equals("b")) {
			synchronized (lock2) {
				try {
					System.out.println("username = " + username);
					Thread.sleep(3000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				
				synchronized (lock1) {
					System.out.println("按lock2 -> lock1代码顺序执行了");
				}
			}
		}
	}

}
