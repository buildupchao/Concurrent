package com.jangz.concurrent.discover.research.simplify;

import java.util.UUID;

public class PCOnBlockingQueue<E> {

	private final PCBlockingQueue<E> pc;

	public PCOnBlockingQueue(PCBlockingQueue<E> pc) {
		super();
		this.pc = pc;
	}
	
	public void consume() {
		for (int i = 0; i < 5; i++) {
			Consumer consumer = new Consumer();
			consumer.setName("C" + i);
			consumer.start();
		}
	}
	
	public void produce() {
		for (int i = 0; i < 5; i++) {
			Producer producer = new Producer();
			producer.setName("P" + i);
			producer.start();
		}
	}
	
	class Producer extends Thread {
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			String x = UUID.randomUUID().toString();
			try {
				pc.put((E) x);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class Consumer extends Thread {
		@Override
		public void run() {
			try {
				pc.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
