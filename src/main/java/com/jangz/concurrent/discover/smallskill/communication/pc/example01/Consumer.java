package com.jangz.concurrent.discover.smallskill.communication.pc.example01;

import com.jangz.concurrent.discover.smallskill.communication.pc.ValueObject;

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
