package com.buildupchao.concurrent.thread.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallableDemo {
	
	public static void main(String[] args) {
		ExecutorService service = Executors.newFixedThreadPool(2);
		service.submit(new MyCallable());
		service.submit(new MyCallable());
		
		service.shutdown();
	}
	
}

class MyCallable implements Callable {

	@Override
	public Object call() throws Exception {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + "::" + i);
		}
		
		return null;
	}
	
}
