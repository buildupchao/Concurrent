package com.buildupchao.concurrent.discover.research.jmm;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class ObjectVolatileExample {
	static volatile Flag flag = new Flag();

	public static void main(String[] args) {
		new Thread(() -> {
			while (true) {
				if ("A".equals(flag.flag)) {
					System.out.println("A");
					flag.flag = "B";
				}
			}
		}).start();
		new Thread(() -> {
			while (true) {
				if ("B".equals(flag.flag)) {
					System.out.println("B");
					flag.flag = "A";
				}
			}
		}).start();
		while (true) {
		}
	}

	static class Flag {
		String flag = "A";
	}
}

