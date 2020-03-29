package com.buildupchao.concurrent.discover.research.simplify;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class PCOnBlockingQueueExample {

	public static void main(String[] args) {
		PCBlockingQueue<String> pc = new PCBlockingQueue<>(3);
		PCOnBlockingQueue<String> tool = new PCOnBlockingQueue<>(pc);
		tool.produce();
		tool.consume();
	}
}
