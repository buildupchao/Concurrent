package zychaowill.discover.research.simplify;

import java.util.Arrays;

public class MagicalLockExpressionExample {

	public static void main(String[] args) {
//		showHoldCount();
//		showQueueLength();
		showWaitQueueLength();
	}
	
	private static void showHoldCount() {
		MagicalLockExpression expression = new MagicalLockExpression();
		expression.serviceHoldCount();
	}
	
	private static void showQueueLength() {
		final MagicalLockExpression expression = new MagicalLockExpression();
		Runnable target = () -> expression.serviceQueueLengh();
		
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(target);
		}
		
		Arrays.stream(threads).forEach(thread -> thread.start());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void showWaitQueueLength() {
		final MagicalLockExpression expression = new MagicalLockExpression();
		Runnable target = () -> expression.serviceWait();
		
		Thread[] threads = new Thread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new Thread(target);
		}
		
		Arrays.stream(threads).forEach(thread -> thread.start());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread signalThread = new Thread(() -> expression.serviceNotify());
		signalThread.start();
	}
}
