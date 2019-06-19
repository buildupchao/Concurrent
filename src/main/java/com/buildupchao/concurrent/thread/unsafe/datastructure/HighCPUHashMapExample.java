package com.buildupchao.concurrent.thread.unsafe.datastructure;

import java.util.HashMap;
import java.util.UUID;

public class HighCPUHashMapExample {

	public static void main(String[] args) throws InterruptedException {
		final HashMap<String, String> map = new HashMap<String, String>(2);
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 1000000000; i++) {
					new Thread(new Runnable() {
						@Override
						public void run() {
							map.put(UUID.randomUUID().toString(), "");
						}
					}, "ftf" + i).start();
				}
			}
		});
		t.start();
		t.join();
	}
}
