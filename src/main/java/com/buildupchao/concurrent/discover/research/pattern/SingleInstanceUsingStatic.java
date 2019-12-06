package com.buildupchao.concurrent.discover.research.pattern;

/**
 * @author buildupchao
 */
public class SingleInstanceUsingStatic {

	private static SingleInstanceUsingStatic instance = null;

	static {
		instance = new SingleInstanceUsingStatic();
	}

	private SingleInstanceUsingStatic() {}

	public static SingleInstanceUsingStatic getInstance() {
		return instance;
	}
}
