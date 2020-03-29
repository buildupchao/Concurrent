package com.buildupchao.concurrent.discover.research.action.datastructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author buildupchao
 * @date 2018/07/11
 * @since JDK1.8
 */
public class SynchronizedListExample {

	public static void main(String[] args) {
		List<Integer> synchronizedList = Collections.synchronizedList(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		synchronizedList.size();
	}
}
