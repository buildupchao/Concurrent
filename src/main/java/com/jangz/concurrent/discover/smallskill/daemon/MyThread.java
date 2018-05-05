package com.jangz.concurrent.discover.smallskill.daemon;

public class MyThread extends Thread {
	
	private int i = 0;
	
	@Override
	public void run() {
		try {
			while (true) {
				i++;
				System.out.println("i=" + (i));
				Thread.sleep(1000);
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}
