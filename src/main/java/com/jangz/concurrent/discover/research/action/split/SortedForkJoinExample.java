package com.jangz.concurrent.discover.research.action.split;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import com.jangz.concurrent.utils.NumberFactory;

public class SortedForkJoinExample {

	public static void main(String[] args) {
		Integer[] elements = NumberFactory.integers(10);
		SortedForkJoin<Integer> sortedForkJoin = new SortedForkJoin<>(elements, Integer::compareTo);

		System.out.printf("Before sort: %s.\n",
				Arrays.stream(elements).map(String::valueOf).collect(Collectors.joining(",")));

		ForkJoinPool pool = new ForkJoinPool();
		pool.execute(sortedForkJoin);
		pool.shutdown();
		
		System.out.printf("After sort: %s.\n",
				Arrays.stream(sortedForkJoin.elements())
					.map(String::valueOf)
					.collect(Collectors.joining(",")));
	}
}
