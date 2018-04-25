package com.jangz.concurrent.discover.smallskill.atomic;

public class Run {
	public static void main(String[] args) {
		try {
			Service service = new Service();
			MyThread[] array = new MyThread[5];
			
			for (int i = 0; i < array.length; i++) {
				array[i] = new MyThread(service);
			}
			
			for (int i = 0; i < array.length; i++) {
				array[i].start();
			}
			Thread.sleep(1000);
			System.out.println(service.aiRef.get());
		} catch (InterruptedException ex) {
			
		}
	}
}
