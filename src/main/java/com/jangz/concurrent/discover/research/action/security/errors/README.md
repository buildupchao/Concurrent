#### 程序中的幽灵：隐蔽的错误

- NoTipErrorExample
```
	v1=1073741827
	v2=1431655768
	average of 1073741827, 1431655768 is -894784850
```


- ArrayListBasedOnConcurrentExample
```
Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 163
	at java.util.ArrayList.add(ArrayList.java:459)
	at zychaowill.discover.research.action.security.errors.ArrayListBasedOnConcurrentExample$AddThread.run(ArrayListBasedOnConcurrentExample.java:13)
	at java.lang.Thread.run(Thread.java:745)
1000136
```

- HashMapBasedOnConcurrentExample

```
由于这段代码很可能占用两个CPU核，并使它们的CPU占有率达到100%。如果CPU性能较弱，可能导致死机。
使用jstack工具显示程序的线程信息。其中jps可以查看当前系统中所有的Java进程。而jstack可以打印给定Java进程的内部线程及其堆栈。（HashMap内部put方法，HashMap链表成环时，迭代就等同于一个死循环。）
```

![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-03_141639.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-03_141839.bmp)

<br/>
<br/>
<br/>

##### Reference Link

- [这篇文章生动形象地描述了线程和进程](http://www.qnx.com/developers/docs/6.4.1/neutrino/getting_started/s1_procs.html)

- [有关线程的状态机](http://www.cnblogs.com/skywang12345/p/3479024.html)

- [对线程中断给出极其详细的描述](http://ibruce.info/2013/12/19/how-to-stop-a-java-thread/)

- [对Java虚拟机的Server和Client模式进行了说明](http://www.uucode.net/201406/jvm-server-client-mode)

- [线程组的概念与使用](http://ifeve.com/thread-management-11/)

- [有关守护线程详尽描述](http://blog.csdn.net/lcore/article/details/12280027)

- [HashMap在多线程卡死的细节分析](http://coolshell.cn/articles/9606.html)

- [WeakHashMap多线程卡死的实际案例](http://www.uucode.net/201412/weakhashmap-endless-loop)

- [HashMap的实现原理](http://www.uucode.net/201503/hashmap-hash-col)