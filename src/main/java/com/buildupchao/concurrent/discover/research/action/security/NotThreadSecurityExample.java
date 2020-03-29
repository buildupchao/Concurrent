package com.buildupchao.concurrent.discover.research.action.security;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class NotThreadSecurityExample implements Runnable {
	static int i = 0;
	
	public static synchronized void increase() {
		i++;
	}

	@Override
	public void run() {
		for (int j = 0; j < 10_000_000; j++)
			increase();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new NotThreadSecurityExample());
		Thread t2 = new Thread(new NotThreadSecurityExample());
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(i);
	}
}
