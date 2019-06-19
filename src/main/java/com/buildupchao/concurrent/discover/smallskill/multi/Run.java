package com.buildupchao.concurrent.discover.smallskill.multi;

public class Run {
	public static void main(String[] args) {
		MyList list = new MyList();
		MyThreadA ta = new MyThreadA(list);
		ta.setName("A");
		ta.start();
		MyThreadB tb = new MyThreadB(list);
		tb.setName("B");
		tb.start();
	}
}

class MyThreadA extends Thread {
	private MyList list;

	public MyThreadA(MyList list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			list.add("threadA" + (i + 1));
		}
	}
}

class MyThreadB extends Thread {
	private MyList list;

	public MyThreadB(MyList list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 100000; i++) {
			list.add("threadB" + (i + 1));
		}
	}
}