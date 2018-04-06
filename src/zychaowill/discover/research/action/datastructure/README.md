JDK提供的并发集合容器大部分在```Java java.util.concurrent ```包中。

- ConcurrentHashMap

线程安全的HashMap：
```Java
public static Map m = Collections.synchronizedMap(new HashMap());
```
Collections.synchronizedMap()会生成一个名为SynchronizedMap的Map。它使用委托，将自己所有Map相关的功能交给传入的HashMap实现，而自己则主要负责保证线程安全。<br/>
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_162830.bmp)

- CopyOnWriteArrayList

- ConcurrentLinkedQueue

- BlockingQueue

- ConcurrentSkipListMap