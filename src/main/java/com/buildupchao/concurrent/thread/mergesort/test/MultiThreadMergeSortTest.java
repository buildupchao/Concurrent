package com.buildupchao.concurrent.thread.mergesort.test;

import java.util.Random;

import com.buildupchao.concurrent.thread.mergesort.SortStrategy;
import com.buildupchao.concurrent.thread.mergesort.impl.MultiThreadMergeSort;

public class MultiThreadMergeSortTest {
	
	public static void main(String[] args) {
		int[] rawArray = new int[40_000_000];
		Random r = new Random();
		
		for (int i = 0; i < 40_000_000; i++) {
			rawArray[i] = r.nextInt(40_000_000);
		}
		
		long startTime = System.currentTimeMillis();
		SortStrategy mergeSort = new MultiThreadMergeSort();
		mergeSort.sort(rawArray);
		long endTime = System.currentTimeMillis();
		
		System.out.println((endTime - startTime) / 1000.0);
	}
}
