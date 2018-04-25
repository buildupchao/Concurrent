package com.jangz.concurrent.discover.research.pattern;

import java.util.Objects;

public class SingleInstanceLazy {

	private volatile static SingleInstanceLazy instance;
	
	public static SingleInstanceLazy newInstance() {
		if (Objects.isNull(instance)) {
			synchronized (SingleInstanceLazy.class) {
				if (Objects.isNull(instance))
					instance = new SingleInstanceLazy();
			}
		}
		return instance;
	}
}
