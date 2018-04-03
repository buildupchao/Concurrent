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