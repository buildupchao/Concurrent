package com.buildupchao.concurrent.discover.smallskill.dirty;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		MyOneList list = new MyOneList();
		MyThreadA ta = new MyThreadA(list);
		ta.setName("A");
		ta.start();
		
		MyThreadB tb = new MyThreadB(list);
		tb.setName("B");
		tb.start();
		
		Thread.sleep(6000);
		System.out.println("listSize=" + list.getSize());
	}
}
