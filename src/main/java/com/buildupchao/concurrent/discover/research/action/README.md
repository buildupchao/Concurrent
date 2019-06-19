- Re-Entrant-Lock重入锁（可以反复进入，这里的反复仅仅局限于一个线程。）
	- 中断响应：ReentrantLock#lockInterruptibly
	- 锁申请等待限时
	- 公平锁

- 重入锁的好搭档：Condition条件
	- 在JDK内部，重入锁和Condition对象被广泛地使用，以ArrayBlockingQueue为例

- 允许多个线程同时访问：信号量（Semaphore）

- 倒计时器：CountDownLatch

- 循环栅栏：CyclicBarrier

- 包结构介绍:
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