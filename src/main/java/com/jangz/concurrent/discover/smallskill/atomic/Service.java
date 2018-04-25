package com.jangz.concurrent.discover.smallskill.atomic;

import java.util.concurrent.atomic.AtomicLong;

public class Service {
	public static AtomicLong aiRef = new AtomicLong();
	
	public synchronized void addNum() {
		System.out.println(Thread.currentThread().getName() + "加了100之后的值是:" + aiRef.addAndGet(100));
		aiRef.addAndGet(1);
	}
}
