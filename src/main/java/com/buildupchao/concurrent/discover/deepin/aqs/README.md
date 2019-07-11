## AbstractQueuedSynchronizer(AQS)

AQS提供了一种实现阻塞锁和一系列FIFO等待队列的同步器的框架，如下图所示。AQS为一系列同步器依赖于一个单独的原子变量（state）的同步器提供了一个非常有用的基础。子类们必须定义改变state变量的protected方法，这些方法定义了state是如何被获取或释放的。鉴于此，本类中的其他方法执行所有的排队和阻塞机制。子类也可以维护其他的state变量，但是为了保证同步，必须原子地操作这些变量。

![](https://github.com/buildupchao/ImgStore/blob/master/Java/concurrent/AQS/aqs.png?raw=true)


AQS对state的操作是原子的，且不能被继承。所有的同步机制的实现均依赖于对改变变量的原子操作。为了实现不同的同步机制，我们需要创建一个非共有的（non-public internal）扩展了AQS类的内部辅助类来实现相应的同步逻辑。AbstractQueuedSynchronizer并不实现任何同步接口，它提供了一些可以被具体实现类直接调用的一些原子方法来重写相应的同步逻辑。AQS同时提供了互斥模式（exclusive）和共享模式（shared）两种不同的同步逻辑。一般情况下，自雷只需要根据需求实现其中一种模式，当然也有同时实现两种模式的同步类，如ReadWriteLock。

AQS定义两种资源共享方式：Exclusive（独占，只有一个线程执行，如ReentrantLock）和Share（共享，多个线程可同时执行，如Semaphore/CountDownLatch）。

不同的自定义同步器争用共享资源的方式也不同。自定义同步器在实现时只需要实现共享资源state的获取与释放方式即可，至于具体线程等待队列的维护（如获取资源失败入队/唤醒出兑等），AQS已经在顶层实现好了。自定义同步器实现时主要实现以下几种方法：
```
isHeldExecusively(): 该线程是否正在独占资源。只有用到condition才需要实现它。

tryAcquire(int): 独占方式。尝试获取资源，成功则返回true，失败则返回false。

tryRelease(int): 独占方式。尝试释放资源，成功则返回true，失败则返回false。

tryAcquireShared(int): 共享方式。尝试获取资源。负数表示失败；0表示成功，但没有剩余资源；正数表示成功，且有剩余资源。

tryReleaseShared(int): 共享方式。尝试释放资源，如果释放后允许唤醒后续等待节点返回true，否则返回false。
```

[详情请戳这里](https://www.jianshu.com/p/da9d051dcc3d)