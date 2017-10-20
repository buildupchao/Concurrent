package zychaowill.discover.smallskill.synchronize;

public class Run {
	public static void main(String[] args) {
//		Task task = new OldTask();
		Task task = new NewTask();
		new ThreadA(task).start();
		new ThreadB(task).start();
	}
}

class ThreadA extends Thread {
	private Task task;

	public ThreadA(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.doLongTimeTask();
	}
}

class ThreadB extends Thread {
	private Task task;

	public ThreadB(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.doLongTimeTask();
	}
}