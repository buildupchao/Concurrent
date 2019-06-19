package com.buildupchao.concurrent.discover.research.simplify;

import lombok.AllArgsConstructor;

public class ReentrantLockConditionExample {

	public static void main(String[] args) throws InterruptedException {
		ReentrantLockCondition condition = new ReentrantLockCondition();
		ReentrantLockConditionThread t1 = new ReentrantLockConditionThread(condition);
		t1.setName("A");
		t1.start();
		ReentrantLockConditionThreadSecond t2 = new ReentrantLockConditionThreadSecond(condition);
		t2.setName("B");
		t2.start();
		Thread.sleep(2000);
		condition.signal();
	}
	
	@AllArgsConstructor
	static class ReentrantLockConditionThread extends Thread {
		private ReentrantLockCondition condition;
		
		@Override
		public void run() {
			condition.await();
		}
	}
	
	@AllArgsConstructor
	static class ReentrantLockConditionThreadSecond extends Thread {
		private ReentrantLockCondition condition;
		
		@Override
		public void run() {
			condition.awaitAgain();
		}
	}
}
