package com.buildupchao.concurrent.discover.research.action.unsafe.cases;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class SequenceThreadLocal implements Sequence {

	private static ThreadLocal<Integer> numberContainer = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}
	};
	
	@Override
	public int getNumber() {
		numberContainer.set(numberContainer.get() + 1);
		return numberContainer.get();
	}

}
