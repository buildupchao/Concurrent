package com.buildupchao.concurrent.thread.mergesort;

import java.util.Arrays;
import java.util.Date;

/**
 * 
 * @Description 舞台类，专门用来测试算法的时间
 * @author yachao
 * @date 2017年4月12日
 * @version V1.0
 */
public class Stage {

	public static void main(String[] args) {
		int[] opacity = {100, 1000, 10_000, 100_000, 1_000_000, 10_000_000};
		long beginTime = 0;
		long endTime = 0;

		for (int i = 0; i < opacity.length; i++) {
			System.out.println("--------------------------------" + opacity[i] + "个数据");
			// 生成排序数组
			int[] rawArr = generateIntArray(opacity[i]);
			int[] rawArr2 = Arrays.copyOf(rawArr, rawArr.length);

			beginTime = new Date().getTime();
			new SingleThreadMergeSort().sort(rawArr);
			endTime = new Date().getTime();
			System.out.println("单线程归并排序花费时间：" + (endTime - beginTime));
			// System.out.println("是否升序：" + CommonUtil.isSorted(rawArr, true));

			beginTime = new Date().getTime();
			new ForkJoinMergeSort().sort(rawArr2);
			endTime = new Date().getTime();
			System.out.println("Fork/Join归并排序花费时间：" + (endTime - beginTime));
			// System.out.println("是否升序：" + CommonUtil.isSorted(rawArr2, true));
		}
	}

	private static int[] generateIntArray(int length) {
		int[] intArr = new int[length];
		for (int i = 0; i < length; i++) {
			intArr[i] = new Double(Math.random() * length).intValue();
		}
		return intArr;
	}
}
