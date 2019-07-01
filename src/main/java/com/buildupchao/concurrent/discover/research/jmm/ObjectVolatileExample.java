package com.buildupchao.concurrent.discover.research.jmm;

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
}

class Flag {
	String flag = "A";
}
