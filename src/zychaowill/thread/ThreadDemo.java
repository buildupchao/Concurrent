package zychaowill.thread;

public class ThreadDemo {
	
	public static void main(String[] args) {
		new MyRunnable2().run();
//		new Thread(new MyRunnable2()).start();
	}
	
}

class MyRunnable2 implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + "::run::" + i);
		}
	}
	
}
