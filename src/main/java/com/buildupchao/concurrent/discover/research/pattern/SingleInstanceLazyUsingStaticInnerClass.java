package com.buildupchao.concurrent.discover.research.pattern;

import java.io.ObjectStreamException;
import java.io.Serializable;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     基于类初始化的解决方案：
 *     JVM在类的初始化阶段（即在 Class 被加载后，且被线程使用之前），会执行类的初始化。
 *  在执行类的初始化期间，JVM会去获取一个锁。这个锁可以同步多个线程对同一个类的初始化。
 * </p>
 *
 * @author buildupchao
 */
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
