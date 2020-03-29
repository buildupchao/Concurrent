package com.buildupchao.concurrent.discover.research.action.split;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class SortedForkJoin<T> extends RecursiveAction {

	private static final long serialVersionUID = -8399694888847226385L;

	private T[] elements;
	private Comparator<T> comparator;
	private int THRESHOLDS = 30;
	private int low;
	private int high;
	
	public SortedForkJoin(T[] elements, Comparator<T> comparator) {
		super();
		this.elements = elements;
		this.comparator = comparator;
		this.low = 0;
		this.high = elements.length;
	}
	
	private SortedForkJoin(T[] elements, int low, int high) {
		this.elements = elements;
		this.low = low;
		this.high = high;
	}

	@Override
	protected void compute() {
		int lowIndex = low;
		int highIndex = high - 1;
		if ((highIndex - lowIndex + 1) <= THRESHOLDS) {
			quickSort(elements, lowIndex, highIndex);
		} else {
			int pivot = partition(elements, lowIndex, highIndex);
			invokeAll(new SortedForkJoin<>(elements, lowIndex, pivot - 1),
					new SortedForkJoin<>(elements, pivot + 1, highIndex));
		}
	}
	
	private int partition(T[] el, int low, int high) {
		int i = low;
		int j = high;
		T base = el[low];
		while (i < j) {
			while (i < j && comparator.compare(el[j], base) >= 0)
				j--;
			el[i] = el[j];
			while (i < j && comparator.compare(el[i], base) <= 0)
				i++;
			el[j] = el[i];
		}
		el[i] = base;
		return i;
	}
	
	private void quickSort(T[] el, int low, int high) {
		if (low >= high)
			return;
		int middle = partition(el, low, high);
		quickSort(el, low, middle - 1);
		quickSort(el, middle + 1, high);
	}
	
	public T[] elements() {
		return elements;
	}
}
