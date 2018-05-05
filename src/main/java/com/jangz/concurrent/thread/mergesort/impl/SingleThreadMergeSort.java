package com.jangz.concurrent.thread.mergesort.impl;

import java.util.Arrays;

import com.jangz.concurrent.thread.mergesort.SortStrategy;

public class SingleThreadMergeSort implements SortStrategy {

	@Override
	public int[] sort(int[] rawArray) {
		mergeSort(rawArray);
		return rawArray;
	}

	private void mergeSort(int[] rawArray) {
		if (rawArray.length > 1) {
			int[] leftArray = Arrays.copyOfRange(rawArray, 0, rawArray.length / 2);
			int[] rightArray = Arrays.copyOfRange(rawArray, rawArray.length / 2, rawArray.length);

			mergeSort(leftArray);
			mergeSort(rightArray);

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
