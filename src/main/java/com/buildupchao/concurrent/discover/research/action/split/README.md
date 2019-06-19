ForkJoinPool的一个重要的接口：

```Java
public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task)
```

你可以向ForkJoinPool线程池提交一个ForkJoinTask任务。所谓ForkJoinTask任务就是支持fork()分解以及join()等待的任务。ForkJoinTask有两个重要的子类，RecursiveAction和RecursiveTask。它们分别表示没有返回值的任务和可以携带返回值的任务。<br/>
<br/>
<br/>
在使用ForkJoin时需要注意，如果任务的划分层次很深，一直得不到返回，那么可能出现两种情况：
- 系统内的线程数量越积越多，导致性能严重下降。

- 函数的调用层次变得很深，最终导致栈溢出。

（Note:不同版本的JDK内部实现机制可能有差异，从而导致其表现不同。）
<br/>
![](https://github.com/buildupchao/ImgStore/blob/master/Java/images/2018-04-06_162051.bmp)
<br/>
<br/>

![](https://github.com/buildupchao/ImgStore/blob/master/Java/images/2018-04-06_161255.bmp)
![](https://github.com/buildupchao/ImgStore/blob/master/Java/images/2018-04-06_160839.bmp)