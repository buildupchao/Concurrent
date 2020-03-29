package com.buildupchao.concurrent.discover.research.action.unsafe.datastructure;

import java.util.HashMap;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class UnsafeHashMapExample {
	
	public static void main(String[] args) throws InterruptedException {
		HashMap<String, String> map = new HashMap<>();
		
		Thread t1 = new Thread(() -> {
			for (int i = 0; i < 25; i++) {
				map.put(String.valueOf(i), String.valueOf(i));
			}
		});
		
		Thread t2 = new Thread(() -> {
			for (int i = 25; i < 50; i++) {
				map.put(String.valueOf(i), String.valueOf(i));
			}
		});
		t1.start();
		t2.start();
		
		Thread.sleep(2000);
		for (int i = 0; i < 50; i++) {
			if (!String.valueOf(i).equals(map.get(String.valueOf(i)))) {
				System.err.println(String.valueOf(i) + ":" + map.get(String.valueOf(i)));
			}
		}
	}
}
