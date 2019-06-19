package com.buildupchao.concurrent.discover.smallskill.dirty;

public class MyThreadB extends Thread {
	
	private MyOneList list;

	public MyThreadB(MyOneList list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "B");
	}
}
