package com.jangz.concurrent.discover.research.pattern;

import java.io.ObjectStreamException;
import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SingleInstanceLazyUsingStaticInnerClass implements Serializable {

	private static final long serialVersionUID = 3049912803425973630L;

	private static class SingleInstanceLazyHelper {
		private static SingleInstanceLazyUsingStaticInnerClass instance = new SingleInstanceLazyUsingStaticInnerClass();
	}

	private SingleInstanceLazyUsingStaticInnerClass() {}
	
	public static SingleInstanceLazyUsingStaticInnerClass getInstance() {
		return SingleInstanceLazyHelper.instance;
	}
	
	protected Object readResolve() throws ObjectStreamException {
		log.info("[readResolve] calling this method.");
		return SingleInstanceLazyHelper.instance;
	}
}
