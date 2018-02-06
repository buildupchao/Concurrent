package zychaowill.discover.research.simplify;

import lombok.AllArgsConstructor;

public class ReentrantLockExpressionExample {
	
	public static void main(String[] args) {
		ReentrantLockExpression service = new ReentrantLockExpression();
		ReentrantLockThread t1 = new ReentrantLockThread(service);
		t1.setName("A1");
		t1.start();
		ReentrantLockThreadSecond t2 = new ReentrantLockThreadSecond(service);
		t2.setName("A2");
		t2.start();

		TreentrankLockAgainThread t3 = new TreentrankLockAgainThread(service);
		t3.setName("B1");
		t3.start();
		TreentrankLockAgainThreadSecond t4 = new TreentrankLockAgainThreadSecond(service);
		t4.setName("B2");
		t4.start();
	}
	
	@AllArgsConstructor
	static class ReentrantLockThread extends Thread {
		private ReentrantLockExpression expression;
		
		@Override
		public void run() {
			expression.tryReentrantLock();
		}
	}
	
	@AllArgsConstructor
	static class ReentrantLockThreadSecond extends Thread {
		private ReentrantLockExpression expression;
		
		@Override
		public void run() {
			expression.tryReentrantLock();
		}
	}
	
	@AllArgsConstructor
	static class TreentrankLockAgainThread extends Thread {
		private ReentrantLockExpression expression;
		
		@Override
		public void run() {
			expression.tryReentrantLockAgain();
		}
	}
	
	@AllArgsConstructor
	static class TreentrankLockAgainThreadSecond extends Thread {
		private ReentrantLockExpression expression;
		
		@Override
		public void run() {
			expression.tryReentrantLockAgain();
		}
	}
}
