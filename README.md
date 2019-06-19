# Concurrent
并发同步、锁等案例

## 多线程

- [生产者与消费者案例（Java 8 实现）](https://github.com/buildupchao/Concurrent/blob/master/src/main/java/com/buildupchao/concurrent/thread/basic/ProducerAndConsumer.java)

- [ReentrantLock](https://github.com/buildupchao/Concurrent/tree/master/src/main/java/com/buildupchao/concurrent/discover/research/simplify)

- [SingleInstance](https://github.com/buildupchao/Concurrent/tree/master/src/main/java/com/buildupchao/concurrent/discover/research/pattern)

- [Socket通信（责任分离案例）](https://github.com/buildupchao/Concurrent/tree/master/src/main/java/com/buildupchao/concurrent/discover/research/action/net)

- [Good 案例](https://github.com/buildupchao/Concurrent/tree/master/src/main/java/com/buildupchao/concurrent/discover/research/action)
	- action:
	   - datastructure: 数据结构类
	   - future: Future相关
	   - inlock: 深入锁的应用，CountDownLatch && CyclicBarrier && Semaphore & LockSupport
	   - net: 网络通讯之责任分离小应用案例
	   - pool: 线程池相关
	   - security: 线程安全性
	   - split: Fork/Join思想及应用案例
	- cache: 缓存案例
	- jmm: Java内存模型：原子性、可见性
	- pattern: 单利模式5种实现方式
	- simplify: 锁的运用
	- timer: 定时器案例