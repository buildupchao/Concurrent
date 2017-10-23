package zychaowill.discover.smallskill.pc.example02;

public class ThreadP extends Thread {
	private Producer producer;

	public ThreadP(Producer producer) {
		super();
		this.producer = producer;
	}
	
	@Override
	public void run() {
		while (true) {
			producer.setValue();
		}
	}
}
