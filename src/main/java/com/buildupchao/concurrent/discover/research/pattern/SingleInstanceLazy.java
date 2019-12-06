package com.buildupchao.concurrent.discover.research.pattern;

/**
 * @author buildupchao
 */
public class SingleInstanceLazy {

	private volatile static SingleInstanceLazy instance;
	
	public static SingleInstanceLazy newInstance() {
		if (instance == null) {
			synchronized (SingleInstanceLazy.class) {
				if (instance == null) {
					instance = new SingleInstanceLazy();
				}
			}
		}
		return instance;
	}
}
