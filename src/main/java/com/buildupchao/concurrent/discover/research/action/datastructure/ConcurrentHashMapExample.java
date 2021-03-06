package com.buildupchao.concurrent.discover.research.action.datastructure;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @see ConcurrentHashMap
 * @see com.buildupchao.concurrent.discover.research.action.unsafe.datastructure.ConcurrentHashMapDeadLoopExample
 *
 * @author buildupchao
 * @date 2018/05/20
 * @since
 */
public class ConcurrentHashMapExample {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
		
		for (int i = 0; i < 10; i++) {
			// Please focus on, both 'put' and 'putIfAbsent' methods hold return value.
			map.putIfAbsent(String.valueOf(i), i);
		}
	}
}
