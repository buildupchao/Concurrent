package com.buildupchao.concurrent.discover.research.jmm;

/**
 * @see
 *
 * @author jangz
 * @since
 */
public class AtomicityVolatileExample {

	public volatile static long t = 0;
	
	public static class ChangeT implements Runnable {
		private long to;
		
		public ChangeT(long to) {
			this.to = to;
		}
		
		@Override
		public void run() {
			while (true) {
				AtomicityVolatileExample.t = to;
				Thread.yield();
			}
		}
	}
	
	public static class ReadT implements Runnable {
		
		@Override
		public void run() {
			while (true) {
				long tmp = AtomicityVolatileExample.t;
				if (tmp != 111L && tmp != -999L && tmp != 333L && tmp != -444L)
					System.out.println(tmp);
				Thread.yield();
			}
		}
	}
	
	public static void main(String[] args) {
		new Thread(new ChangeT(111L)).start();
		new Thread(new ChangeT(-999L)).start();
		new Thread(new ChangeT(333L)).start();
		new Thread(new ChangeT(-444L)).start();
		new Thread(new ReadT()).start();
	}
}
