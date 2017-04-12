package zychaowill.thread.mergesort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * 
 * @Description Fork/Join框架实现归并排序（Fork/Join是从JDK1.7加入的并发计算框架）
 * @author yachao
 * @date 2017年4月12日
 * @version V1.0
 */
public class ForkJoinMergeSort implements SortStrategy {

	@Override
	public int[] sort(int[] rawArray) {
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(new MergeSort(rawArray));
		return rawArray;
	}

	/**
	 * 
	 * @Description 使用Fork/Join的方式进行归并排序，充分利用cpu
	 * @author yachao
	 * @date 2017年4月12日
	 * @version V1.0
	 */
	public static class MergeSort extends RecursiveAction {

		private static final long serialVersionUID = 6432344528875959985L;
		private int[] intArr;

		public MergeSort(int[] intArr) {
			this.intArr = intArr;
		}

		@Override
		protected void compute() {
			if (intArr.length > 1) {
				// 如果数组长度大于1就分解成两份
				int[] leftArray = Arrays.copyOfRange(intArr, 0, intArr.length / 2);
				int[] rightArray = Arrays.copyOfRange(intArr, intArr.length / 2, intArr.length);
				
				// 这里分成两份执行
				invokeAll(new MergeSort(leftArray), new MergeSort(rightArray));
				
				// 合并且排序
				merge(leftArray, rightArray, intArr);
			}
		}

		/**
		 * 合并排序
		 * 
		 * @param leftArray
		 * @param rightArray
		 * @param intArr
		 */
		private void merge(int[] leftArray, int[] rightArray, int[] intArr) {
			// i: leftArray数组索引，j: rightArray数组索引，k: intArr数组索引
			int i = 0, j = 0, k = 0;
			while (i < leftArray.length && j < rightArray.length) {
				// 当两个数组中都有值的时候，比较当前元素进行选择
				if (leftArray[i] < rightArray[j]) {
					intArr[k] = leftArray[i];
					i++;
				} else {
					intArr[k] = rightArray[j];
					j++;
				}
				k++;
			}
			// 将还剩余没遍历完的数组元素直接追加到intArr后面
			if (i == leftArray.length) {
				for (; j < rightArray.length; j++, k++) {
					intArr[k] = rightArray[j];
				}
			} else {
				for (; i < leftArray.length; i++, k++) {
					intArr[k] = leftArray[i];
				}
			}
		}
	}

}
