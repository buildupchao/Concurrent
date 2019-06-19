package com.buildupchao.concurrent.discover.smallskill.communication.pc.example01;

public class Run {
	public static void main(String[] args) {
		String lock = new String("");
		Producer p = new Producer(lock);
		Consumer c = new Consumer(lock);
		
		new ThreadP(p).start();
		new ThreadC(c).start();
	}
}
