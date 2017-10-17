package zychaowill.discover.smallskill;

public class TestSyn implements Runnable {
	
	int b = 100;
	
	synchronized void m1() throws InterruptedException {
		b = 1000;
		Thread.sleep(500); // step 6
		System.out.println("b = " + b);
	}
	
	synchronized void m2() throws InterruptedException {
		Thread.sleep(250); // step 5
		b = 2000;
	}
	
	public static void main(String[] args) throws InterruptedException {
		TestSyn testSyn = new TestSyn();
		Thread t = new Thread(testSyn); // step 1
		t.start(); // step 2
		
		testSyn.m2(); // step 3
		System.out.println("main thread b = " + testSyn.b); // step 4
	}
	
	@Override
	public void run() {
		try {
			m1();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
