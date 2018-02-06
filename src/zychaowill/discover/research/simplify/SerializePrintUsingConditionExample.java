package zychaowill.discover.research.simplify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializePrintUsingConditionExample {
	
	private static volatile int nextPrintWho = 1;
	private static ReentrantLock lock = new ReentrantLock();
	private static final Condition conditionA = lock.newCondition();
	private static final Condition conditionB = lock.newCondition();
	private static final Condition conditionC = lock.newCondition();
	
	public static void main(String[] args) {
		Thread aPrinter = getAPrinter();
		Thread bPrinter = getBPrinter();
		Thread cPrinter = getCPrinter();
		
		Thread[] aThreads = new Thread[5];
		Thread[] bThreads = new Thread[5];
		Thread[] cThreads = new Thread[5];
		for (int i = 0; i < 5; i++) {
			aThreads[i] = new Thread(aPrinter);
			bThreads[i] = new Thread(bPrinter);
			cThreads[i] = new Thread(cPrinter);
			aThreads[i].start();
			bThreads[i].start();
			cThreads[i].start();
		}
	}
	
	private static Thread getAPrinter() {
		return new Thread(() -> {
			try {
				lock.lock();
				while (nextPrintWho != 1)
					conditionA.await();
				for (int i = 0; i < 3; i++)
					log.info("Printer A {}.", (i + 1));
				nextPrintWho = 2;
				conditionB.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		});
	}
	
	private static Thread getBPrinter() {
		return new Thread(() -> {
			try {
				lock.lock();
				while (nextPrintWho != 2)
					conditionB.await();
				for (int i = 0; i < 3; i++)
					log.info("Printer B {}.", (i + 1));
				nextPrintWho = 3;
				conditionC.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		});
	}
	
	private static Thread getCPrinter() {
		return new Thread(() -> {
			try {
				lock.lock();
				while (nextPrintWho != 3)
					conditionC.await();
				for (int i = 0; i < 3; i++)
					log.info("Printer C {}.", (i + 1));
				nextPrintWho = 1;
				conditionA.signalAll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		});
	}
}
