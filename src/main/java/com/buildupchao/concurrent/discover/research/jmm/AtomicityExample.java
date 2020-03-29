package com.buildupchao.concurrent.discover.research.jmm;

/**
 * Will generate error numbers, if based on 32-bit JVM.
 * @see
 *
 * @author buildupchao
 * @date 2018/05/20
 * @since
 */
public class AtomicityExample {

	public static long t = 0;
	
	public static class ChangeT implements Runnable {
		private long to;
		
		public ChangeT(long to) {
			this.to = to;
		}
		
		@Override
		public void run() {
			while (true) {
				AtomicityExample.t = to;
				Thread.yield();
			}
		}
	}
	
	public static class ReadT implements Runnable {
		
		@Override
		public void run() {
			while (true) {
				long tmp = AtomicityExample.t;
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
