package com.buildupchao.concurrent.discover.smallskill.communication.pc.example02;

public class ThreadC extends Thread {
	private Consumer consumer;

	public ThreadC(Consumer consumer) {
		super();
		this.consumer = consumer;
	}
	
	@Override
	public void run() {
		while (true) {
			consumer.getValue();
		}
	}
}
