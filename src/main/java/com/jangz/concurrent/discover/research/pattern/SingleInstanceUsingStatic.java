package com.jangz.concurrent.discover.research.pattern;

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
