package com.buildupchao.concurrent.discover.smallskill.dirty;

public class MyThreadA extends Thread {
	
	private MyOneList list;

	public MyThreadA(MyOneList list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		MyService msRef = new MyService();
		msRef.addServiceMethod(list, "A");
	}
}
