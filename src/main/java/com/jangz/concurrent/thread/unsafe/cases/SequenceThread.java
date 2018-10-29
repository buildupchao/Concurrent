package com.jangz.concurrent.thread.unsafe.cases;

public class SequenceThread extends Thread {

	private Sequence sequence;

	public SequenceThread(Sequence sequence) {
		super();
		this.sequence = sequence;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
		}
	}
}
