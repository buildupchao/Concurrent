package com.buildupchao.concurrent.thread.reorder;

public class InstructionReOrderExample {
	
	static int x = 0;
	static int y = 0;
	static int a = 0;
	static int b = 0;
	static int count = 0;
	
	public static void main(String[] args) throws InterruptedException {
		while (true) {
			++count;
			x = y = a = b = 0;
			Thread one = new Thread(() -> {
				a = 1;
				x = b;
			});
			
			Thread other = new Thread(() -> {
				b = 1;
				y = a;
			});
			one.start();
			other.start();
			one.join();
			other.join();
			System.out.printf("第%d次，(%d, %d)\n", count, x, y);
			if (x == 0 && y == 0) 
				break;
		}
	}
}
