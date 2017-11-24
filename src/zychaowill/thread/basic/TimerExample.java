package zychaowill.thread.basic;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample {

	public static void main(String[] args) {
		Timer t = new Timer();
		t.schedule(new MyTask(), 3000);
	}
	
}

class MyTask extends TimerTask {

	@Override
	public void run() {
		System.out.println("您该起床了!");
	}
	
}
