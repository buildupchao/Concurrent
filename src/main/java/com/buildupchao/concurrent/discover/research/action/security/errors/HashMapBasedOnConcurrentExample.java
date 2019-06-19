package com.buildupchao.concurrent.discover.research.action.security.errors;

import java.util.HashMap;

/*
 	三种情况：
 	（1）程序正常结束，并且结果也是符合预期的。HashMap的大小为100,000
 	（2）程序正常结束，但结果不符合预期，而是一个小于100,000的数字，比如98868
 	（3）程序永远无法结束。（由于多线程的冲突，这个链表的结构已经遭到了破坏，链表成环了！当链表成环时，上述的迭代就等同于一个死循环）
 
 */
public class HashMapBasedOnConcurrentExample {
	static HashMap<String, String> map = new HashMap<>();

	public static class AddThread implements Runnable {
		int start = 0;

		public AddThread(int start) {
			super();
			this.start = start;
		}

		@Override
		public void run() {
			for (int i = start; i < 100_000; i += 2)
				map.put(Integer.toString(i), Integer.toBinaryString(i));
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new AddThread(0));
		Thread t2 = new Thread(new AddThread(1));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println(map.size());
	}
}
