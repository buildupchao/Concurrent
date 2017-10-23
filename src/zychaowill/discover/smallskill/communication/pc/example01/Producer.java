package zychaowill.discover.smallskill.communication.pc.example01;

import zychaowill.discover.smallskill.communication.pc.ValueObject;

public class Producer {
	private String lock;

	public Producer(String lock) {
		super();
		this.lock = lock;
	}
	
	public void setValue() {
		try {
			synchronized (lock) {
				if (!ValueObject.value.equals("")) {
					lock.wait();
				}
				String value = System.currentTimeMillis() + "_" + System.nanoTime();
				System.out.println("set的值是" + value);
				ValueObject.value = value;
				lock.notify();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
