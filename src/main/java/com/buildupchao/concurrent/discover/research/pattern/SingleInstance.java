package com.buildupchao.concurrent.discover.research.pattern;

/**
 * @author buildupchao
 */
public class SingleInstance {
	
	private static final SingleInstance instance = new SingleInstance();
	
	public static SingleInstance instance() {
		return instance;
	}
}
