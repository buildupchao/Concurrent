```Java
public ThreadPoolExecutor(int corePoolSize, // 指定了线程池中的线程数量
	int maximumPoolSize, // 指定了线程池中的最大线程数量
	long keepAliveTime, // 当线程池线程数量超过corePoolSize，多余的空闲线程的存货时间。即，超过corePoolSize的空闲线程，在多长时间内，会被销毁。
	TimeUnit unit, // keepAliveTime的单位
	BlockingQueue<Runnable> workQueue, // 任务队列，被提交但尚未被执行的任务
	ThreadFactory threadFactory, // 线程工厂，用于创建线程，一般用默认的即可
	RejectedExecutionHandler handler) // 拒绝策略。当任务太多来不及处理，如何拒绝任务
```

- 使用自定义线程池时，要根据应用的具体情况，选择合适的并发队列作为任务的缓冲。当线程资源紧张时，不同的并发队列对系统行为和性能的影响均不同。

- 拒绝策略

![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-03_153603.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-03_153700.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-03_153715.bmp)

<br/>
<br/>
- ThreadFactory

```Java
	Thread newThread(Runnable r)
```