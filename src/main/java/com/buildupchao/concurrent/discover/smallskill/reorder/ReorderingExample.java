package com.buildupchao.concurrent.discover.smallskill.reorder;

public class ReorderingExample {
	public static void main(String[] args) {
		int x, y;
		x = 1;
		try {
			x = 2;
			y = 0 / 0;
		} catch (Exception e) {
		} finally {
			System.out.println("x = " + x);
		}
	}
}