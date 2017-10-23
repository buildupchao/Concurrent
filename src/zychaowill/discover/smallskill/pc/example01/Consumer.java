package zychaowill.discover.smallskill.pc.example01;

import zychaowill.discover.smallskill.pc.ValueObject;

public class Consumer {
	private String lock;

	public Consumer(String lock) {
		super();
		this.lock = lock;
	}
	
	public void getValue() {
		try {
			synchronized (lock) {
				if (ValueObject.value.equals("")) {
					lock.wait();
				}
				System.out.println("get的值是" + ValueObject.value);
				ValueObject.value = "";
				lock.notify();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
