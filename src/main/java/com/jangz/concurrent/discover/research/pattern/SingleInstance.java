package com.jangz.concurrent.discover.research.pattern;

public class SingleInstance {
	
	private static final SingleInstance instance = new SingleInstance();
	
	public static SingleInstance instance() {
		return instance;
	}
}
