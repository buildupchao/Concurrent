package com.buildupchao.concurrent.discover.research.action.unsafe.cases;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
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
