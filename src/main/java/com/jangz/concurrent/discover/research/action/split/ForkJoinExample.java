package com.jangz.concurrent.discover.research.action.split;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

public class ForkJoinExample<T> extends RecursiveAction {

	private static final long serialVersionUID = 2213814119162737975L;

	final T[] elements;
	final int THRESHOLD = 30;
	final Comparator<T> comparator;
	int low;
	int high;

	public ForkJoinExample(T[] elements, Comparator<T> comparator) {
		super();
		this.elements = elements;
		this.comparator = comparator;
		this.low = 0;
		this.high = elements.length;
	}
	
	public ForkJoinExample(T[] elements, Comparator<T> comparator, int low, int high) {
		this(elements, comparator);
		this.low = low;
		this.high = high;
	}

	@Override
	protected void compute() {
		int low = 0;
		int high = elements.length;
		if (high - low < THRESHOLD)
			sequentiallySort(elements, low, high);
		else {
			int pivot = partition(elements, low, high);
			invokeAll(new ForkJoinExample<>(elements, comparator, low, pivot - 1),
					new ForkJoinExample<>(elements, comparator, pivot + 1, high));
		}
	}

	private int partition(T[] elements, int low, int high) {
		T t = elements[low];
		int i = low - 1;
		
		for (int k = low; k < high; k++) {
			if (comparator.compare(elements[k], t) <= 0) {
				i++;
				swap(elements, i, k);
			}
		}
		swap(elements, i + 1, high);
		return i + 1;
	}

	private void swap(T[] elements, int i, int k) {
		if (i != k) {
			T t = elements[i];
			elements[i] = elements[k];
			elements[k] = t;
		}
	}

	private void sequentiallySort(T[] elements, int low, int high) {
		Arrays.stream(elements).sorted(comparator).collect(Collectors.toList()).toArray(elements);
	}

}
