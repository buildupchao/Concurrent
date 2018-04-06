JDK提供的并发集合容器大部分在``` java.util.concurrent ```包中。

- ConcurrentHashMap

线程安全的HashMap：
```Java
public static Map m = Collections.synchronizedMap(new HashMap());
```
Collections.synchronizedMap()会生成一个名为SynchronizedMap的Map。它使用委托，将自己所有Map相关的功能交给传入的HashMap实现，而自己则主要负责保证线程安全。<br/>
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_162830.bmp)

- 高效读取：不变模式下的CopyOnWriteArrayList
```Java
public static List<String> list = Collections.synchronizedList(new LinkedList<String>());
```
此时生成的List对象 就是线程安全的。

为了将读取的性能发挥到极致，JDK中提供了CopyOnWriteArrayList类。对他来说，读取是完全不用加锁的，并且更好的消息是：写入也不会阻塞读取操作。只有写入和写入之间需要进行同步等待。这样一来，读操作的性能将大幅度提升。<br/>
<br/>
所谓CopyOnWrite就是在写入操作时，进行一次自我复制。换句话说，当这个List需要修改时，我并不修改原有的内容（这对于保证当前在读线程的数据一致性非常重要），而是对原油的数据进行一次复制，将修改的内容写入副本中。写完之后，再将修改完的副本替换原来的数据。这样就可以保证写操作不会影响读了。<br/>
<br/>
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_170548.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_170751.bmp)

- 高效读写的队列：ConcurrentLinkedQueue
```Java
private static class Node<E> {
	volatile E item;
	volatile Node<E> next;
```
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_163816.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_164416.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_164437.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_165316.bmp)
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_165334.bmp)

- 数据共享通道：BlockingQueue
> 对于并发程序而言，高性能自然是一个我们需要追求的目标。但多线程的开发模式还会引入一个问题，那就是如何进行多个线程间的数据共享呢？
<br/>
我们既希望线程A能够通知线程B，又希望线程A不知道线程B的存在。这样，如果将来进行重构或者升级，我们完全可以不修改线程A，而直接把线程B升级为线程C，保证系统的平滑过渡。
<br/>
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_171650.bmp)<br/>
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_171747.bmp)<br/>
![](https://github.com/Zychaowill/ImgStore/blob/master/Java/images/2018-04-06_171819.bmp)
<br/>
- ConcurrentSkipListMap