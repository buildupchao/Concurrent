package zychaowill.thread.mergesort.impl;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import zychaowill.thread.mergesort.SortStrategy;

public class MultiThreadMergeSort implements SortStrategy {

	@Override
	public int[] sort(int[] rawArray) {
		try {
			mergeSort(rawArray);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return rawArray;
	}

	private void mergeSort(int[] rawArray) throws InterruptedException {
		if (rawArray.length > 1) {
			int[] leftArray = Arrays.copyOfRange(rawArray, 0, rawArray.length / 2);
			int[] rightArray = Arrays.copyOfRange(rawArray, rawArray.length / 2, rawArray.length);

			CountDownLatch latch = new CountDownLatch(2);
			new Thread(() -> {
				new SingleThreadMergeSort().sort(leftArray);

				latch.countDown();
			}).start();

			new Thread(() -> {
				new SingleThreadMergeSort().sort(rightArray);

				latch.countDown();
			}).start();

			latch.await();
			merge(leftArray, rightArray, rawArray);
		}
	}

	private void merge(int[] leftArray, int[] rightArray, int[] rawArray) {
		int l = 0, r = 0, k = 0;

		while (l < leftArray.length && r < rightArray.length) {
			if (leftArray[l] > rightArray[r]) {
				rawArray[k++] = leftArray[l++];
			} else {
				rawArray[k++] = rightArray[r++];
			}
		}

		while (r < rightArray.length) {
			rawArray[k++] = rightArray[r++];
		}
		while (l < leftArray.length) {
			rawArray[k++] = leftArray[l++];
		}
	}
}
