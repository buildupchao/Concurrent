package com.buildupchao.concurrent.discover.research.action.unsafe.cases;

public class SequenceExample {
	
	public static void main(String[] args) {
		Sequence sequence = new SequenceMyThreadLocal();
		
		SequenceThread t1 = new SequenceThread(sequence);
		SequenceThread t2 = new SequenceThread(sequence);
		SequenceThread t3 = new SequenceThread(sequence);
		t1.start();
		t2.start();
		t3.start();
	}
}
