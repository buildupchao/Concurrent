package com.buildupchao.concurrent.discover.research.action.split;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SumBasedOnForkJoinExample extends RecursiveTask<Long> {

	private static final long serialVersionUID = 8392032364411100616L;

	private static final int THRESHOLD = 10_000; 
	private long start;
	private long end;

	public SumBasedOnForkJoinExample(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if (canCompute())
			return simpleCompute();
		return computeUsingForkJoin();
	}

	private Long simpleCompute() {
		long sum = 0;
		for (long i = start; i <= end; i++)
			sum += i;
		return sum;
	}
	
	private Long computeUsingForkJoin() {
		long sum = 0;
		long step = (start + end) / 100;
		ArrayList<SumBasedOnForkJoinExample> subTasks = new ArrayList<>();
		long pos = start;
		for (int i = 0; i < 100; i++) {
			long lastOne = pos + step;
			if (lastOne > end)
				lastOne = end;
			SumBasedOnForkJoinExample subTask = new SumBasedOnForkJoinExample(pos, lastOne);
			pos += step + 1;
			subTasks.add(subTask);
			subTask.fork();
		}
		for (SumBasedOnForkJoinExample example : subTasks)
			sum += example.join();
		return sum;
	}
	
	private boolean canCompute() {
		return (end - start) < THRESHOLD;
	}
	
	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		SumBasedOnForkJoinExample example = new SumBasedOnForkJoinExample(0, 30_000L);
		ForkJoinTask<Long> result = pool.submit(example);
		
		try {
			long startTime = System.currentTimeMillis();
			
			long res = result.get();
			System.out.println("sum is " + res + ", cost is " + ((System.currentTimeMillis() - startTime) / 1000.0) + "S.");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
