package com.jangz.concurrent.discover.research.simplify;

public class PCOnBlockingQueueExample {

	public static void main(String[] args) {
		PCBlockingQueue<String> pc = new PCBlockingQueue<>(3);
		PCOnBlockingQueue<String> tool = new PCOnBlockingQueue<>(pc);
		tool.produce();
		tool.consume();
	}
}
