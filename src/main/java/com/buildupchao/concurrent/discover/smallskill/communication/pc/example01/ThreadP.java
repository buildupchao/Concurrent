package com.buildupchao.concurrent.discover.smallskill.communication.pc.example01;

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
