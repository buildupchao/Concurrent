package com.buildupchao.concurrent.discover.research.action.unsafe.cases;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
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
