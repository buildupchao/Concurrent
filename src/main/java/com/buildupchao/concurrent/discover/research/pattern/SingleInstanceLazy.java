package com.buildupchao.concurrent.discover.research.pattern;

/**
 * DCL（双重检查锁定）
 *
 * @author buildupchao
 */
public class SingleInstanceLazy {

	/**
	 * <p>
	 * 这里必须用 volatile 来修饰变量，避免线程内保证 intra-thread semantics的前提下进行的重排序。
	 * 因为创建一个对象过程如下：
	 * <code>
	 *     memory = allocate(); // 1.分配对象的内存空间
	 *     ctorIntance(memory); // 2.初始化对象
	 *     instance = memory;   // 3.设置 instance 指向刚分配的内存地址
	 * </code>
	 * 可能重排序为：
	 * <code>
	 *     memory = allocate(); // 1.分配对象的内存空间
	 *     instance = memory;   // 3.设置 instance 指向刚分配的内存地址（注意，此时对象还没有被初始化！）
	 *     ctorIntance(memory); // 2.初始化对象
	 * </code>
	 *
	 * 如此一来，如果线程A进行初始化时（有重排序，instance 只是指向刚分配的内存空间，还没初始化），线程B判断 instance != null，
	 * 则会进行访问 instance 引用的对象，此时，线程B将会访问到一个还未初始化的对象。
	 * <br/>
	 * 注意：这个解决方案需要JDK 5或者更高版本（因为从JDK 5开始试用新的JSR-133 内存模型规范，
	 * 这个规范增强了 volatile 的语义）
	 * </p>
	 */
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
