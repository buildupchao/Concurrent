package com.buildupchao.concurrent.discover.smallskill.sleep;

public class RunExample02 {
	
	public static void main(String[] args) {
		MyThread thread = new MyThread();
		System.out.println("begin=" + System.currentTimeMillis());
		thread.start();
//		thread.run();
		System.out.println("end=" + System.currentTimeMillis());
	}
}
