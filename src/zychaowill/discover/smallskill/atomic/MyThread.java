package zychaowill.discover.smallskill.atomic;

public class MyThread extends Thread {

	private Service service;

	@Override
	public void run() {
		service.addNum();
	}
	
	public MyThread(Service service) {
		this.service = service;
	}
}
