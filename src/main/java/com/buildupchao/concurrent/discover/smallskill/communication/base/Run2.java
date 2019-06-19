package com.buildupchao.concurrent.discover.smallskill.communication.base;

public class Run2 {
	private String lock = new String("");
	private boolean isFirstRunB = false;

	private Runnable runnableA = () -> {
		try {
			synchronized (lock) {
				while (!isFirstRunB) {
					System.out.println("begin wait");
					lock.wait();
					System.out.println("end wait");
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	};

	private Runnable runnableB = () -> {
		synchronized (lock) {
			if (!isFirstRunB) {
				System.out.println("begin notify");
				lock.notify();
				System.out.println("end notify");
				isFirstRunB = true;
			}
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		Run2 run = new Run2();
		new Thread(run.runnableA).start();
		new Thread(run.runnableB).start();
	}
}
