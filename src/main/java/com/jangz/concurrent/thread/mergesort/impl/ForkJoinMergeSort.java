package com.jangz.concurrent.thread.mergesort.impl;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import com.jangz.concurrent.thread.mergesort.SortStrategy;

public class ForkJoinMergeSort implements SortStrategy {

	@Override
	public int[] sort(int[] rawArray) {
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new MergeSort(rawArray));
		return rawArray;
	}

	private static class MergeSort extends RecursiveAction {

		private static final long serialVersionUID = -7305299371466679664L;
		private int[] rawArray;

		public MergeSort(int[] rawArray) {
			this.rawArray = rawArray;
		}

		@Override
		protected void compute() {
			int[] leftArray = Arrays.copyOfRange(rawArray, 0, rawArray.length / 2);
			int[] rightArray = Arrays.copyOfRange(rawArray, rawArray.length / 2, rawArray.length);

			invokeAll(new MergeSort(leftArray), new MergeSort(rightArray));

			merge(leftArray, rightArray, rawArray);
		}

		private void merge(int[] leftArray, int[] rightArray, int[] intArr) {
			int l = 0, r = 0, k = 0;

			while (l < leftArray.length && r < rightArray.length) {
				if (leftArray[l] < rightArray[r]) {
					intArr[k++] = leftArray[l++];
				} else {
					intArr[k++] = rightArray[r++];
				}
			}

			while (r < rightArray.length) {
				intArr[k++] = rightArray[r++];
			}
			while (l < leftArray.length) {
				intArr[k++] = leftArray[l++];
			}
		}

	}
}
