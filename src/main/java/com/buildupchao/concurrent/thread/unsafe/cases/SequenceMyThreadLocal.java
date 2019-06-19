package com.buildupchao.concurrent.thread.unsafe.cases;

public class SequenceMyThreadLocal implements Sequence {

	private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
		@Override
		public Integer initialValue() {
			return 0;
		}
	};

	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get() + 1);
		return numberContainer.get();
	}

}
