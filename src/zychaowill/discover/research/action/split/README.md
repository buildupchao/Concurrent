ForkJoinPool的一个重要的接口：

```Java
public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task)
```

你可以向ForkJoinPool线程池提交一个ForkJoinTask任务。所谓ForkJoinTask任务就是支持fork()分解以及join()等待的任务。ForkJoinTask有两个重要的子类，RecursiveAction和RecursiveTask。它们分别表示没有返回值的任务和可以携带返回值的任务。

![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_161255.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_160839.bmp)