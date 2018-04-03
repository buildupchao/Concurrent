- Re-Entrant-Lock重入锁（可以反复进入，这里的反复仅仅局限于一个线程。）
	- 中断响应：ReentrantLock#lockInterruptibly
	- 锁申请等待限时
	- 公平锁

- 重入锁的好搭档：Condition条件
	- 在JDK内部，重入锁和Condition对象被广泛地使用，以ArrayBlockingQueue为例

- 允许多个线程同时访问：信号量（Semaphore）

- 倒计时器：CountDownLatch

- 循环栅栏：CyclicBarrier