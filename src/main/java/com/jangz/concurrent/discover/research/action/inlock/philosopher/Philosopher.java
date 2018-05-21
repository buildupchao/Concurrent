package com.jangz.concurrent.discover.research.action.inlock.philosopher;

public class Philosopher implements Runnable {

	private Chopstick left;
	private Chopstick right;

	private int index;
	private int thinkTime;

	public Philosopher(Chopstick left, Chopstick right, int index, int thinkTime) {
		super();
		this.left = left;
		this.right = right;
		this.index = index;
		this.thinkTime = thinkTime;
	}

	@Override
	public void run() {
		try {
			
			while (!Thread.interrupted()) {
				System.out.println(this + " thinking>>>>>>>");
				thinking();
				
				System.out.println(this + " take right stick>>>>>>");
				right.take();
				
				System.out.println(this + " take left stick>>>>>>");
				left.take();
				
				System.out.println(this + " eatting>>>>>>");
				Thread.sleep(2000);
				
				right.drop();
				left.drop();
			}
		} catch (InterruptedException e) {
			System.out.println(this + " InterruptedException>>>>>>");
		}
	}

	private void thinking() throws InterruptedException {
		Thread.sleep(thinkTime * 100);
	}

	@Override
	public String toString() {
		return "Philosopher[" + index + "]";
	}
}
