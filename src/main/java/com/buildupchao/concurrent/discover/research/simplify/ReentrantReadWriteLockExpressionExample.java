package com.buildupchao.concurrent.discover.research.simplify;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class ReentrantReadWriteLockExpressionExample {
	
	public static void main(String[] args) {
//		readLockSharing();
//		writeLockMutex();
//		readWriteMutex();
		writeReadMutex();
	}
	
	private static void readLockSharing() {
		final ReentrantReadWriteLockExpression service = new ReentrantReadWriteLockExpression();
		Runnable target = () -> service.read();
		
		Thread a = new Thread(target);
		a.setName("A");
		Thread b = new Thread(target);
		b.setName("B");
		a.start();
		b.start();
	}
	
	private static void writeLockMutex() {
		final ReentrantReadWriteLockExpression service = new ReentrantReadWriteLockExpression();
		Runnable target = () -> service.write();
		
		Thread a = new Thread(target);
		a.setName("A");
		Thread b = new Thread(target);
		b.setName("B");
		a.start();
		b.start();
	}
	
	private static void readWriteMutex() {
		final ReentrantReadWriteLockExpression service = new ReentrantReadWriteLockExpression();
		
		Thread a = new Thread(() -> service.read());
		a.setName("A");
		Thread b = new Thread(() -> service.write());
		b.setName("B");
		a.start();
		b.start();
	}
	
	private static void writeReadMutex() {
		final ReentrantReadWriteLockExpression service = new ReentrantReadWriteLockExpression();
		
		Thread a = new Thread(() -> service.write());
		a.setName("A");
		Thread b = new Thread(() -> service.read());
		b.setName("B");
		a.start();
		b.start();
	}
}
