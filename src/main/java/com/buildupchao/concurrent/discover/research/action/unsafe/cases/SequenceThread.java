package com.buildupchao.concurrent.discover.research.action.unsafe.cases;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
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
