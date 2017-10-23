package zychaowill.discover.smallskill.pc.example02;

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
					System.out.println("Consumer " + Thread.currentThread().getName() + " WAITING");
					lock.wait();
				}
				System.out.println("Consumer " + Thread.currentThread().getName() + " RUNNABLE");
				ValueObject.value = "";
				lock.notifyAll();
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
