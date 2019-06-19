package com.buildupchao.concurrent.discover.smallskill.stringfinal;

public class ThreadB extends Thread {
	private Service service;

	public ThreadB(Service service) {
		this.service = service;
	}
	
	@Override
	public void run() {
		service.print("AA");
	}
}
