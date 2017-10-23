package zychaowill.discover.smallskill.communication.pc.example02;

public class Run {
	public static void main(String[] args) throws InterruptedException {
		String lock = new String("");
		Producer producer = new Producer(lock);
		Consumer consumer = new Consumer(lock);
		
		ThreadP[] pThread = new ThreadP[2];
		ThreadC[] cThread = new ThreadC[2];
		for (int i = 0; i < 2; i++) {
			pThread[i] = new ThreadP(producer);
			pThread[i].setName("P" + (i + 1));
			cThread[i] = new ThreadC(consumer);
			cThread[i].setName("C" + (i + 1));
			pThread[i].start();
			cThread[i].start();
		}
		
		Thread.sleep(5000);
		Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeCount()];
		Thread.currentThread().getThreadGroup().enumerate(threadArray);
		for (int i = 0; i < threadArray.length; i++) {
			System.out.println(threadArray[i].getName() + " " + threadArray[i].getState());
		}
	}
}
