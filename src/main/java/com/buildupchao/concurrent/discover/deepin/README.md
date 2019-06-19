#### Concurrent Programming

并发编程的目的是为了让程序运行得更快，但是，并不是启动更多的线程就能让程序最大限度地并发执行。在进行并发编程时，如果希望通过多线程执行任务让程序运行得更快，会面临非常多的挑战，比如上下文切换的问题、死锁的问题，以及受限于硬件和软件的资源限制问题。
<br/>
- Q: 如何减少上下文切换？
- A: 无锁并发编程；CAS算法；使用最少线程和使用协程

使用最少的线程：避免创建不需要的线程，比如任务很少，但是创建了很多线程来处理，这样会造成大量线程都处于等待状态。
<br/>
协程：在单线程里实现多任务的调度，并在单线程里维持多个任务间的切换。

```
sudo -u admin /opt/ifeve/java/bin/jstack 3117 > /home/tengfei.fangtf/dump17

grep java.lang.Thread.State dump17 | awk '{print $2$3$4$5}' | sort | uniq -c

打开dump文件查看处于WAITING(onobjectmonitor)的线程在做什么。
（基本全是JBOSS的工作线程，在await。说明JBOSS线程池里线程接收到
的任务太少，大量线程都闲着）

减少JBOSS的工作线程数，找到JBOSS的线程池配置信息，将maxThreads降到100.
<maxThreads="250" maxHttpHeaderSize="8192" emptySessionPath="false"
   minSpareThreads="40" maxSpareThreads="75" maxPostSize="512000"
   protocol="HTTP/1.1" enableLookups="false" redirectPort="8443"
   acceptCount="200" bufferSize="16384" connectTimeout="15000"
   disableUploadTimeout="false" useBodyEncodingForURI="true">
   
重启JBOSS，在dump线程信息，然后统计WAITING(onobjectmonitor)的线程，
发现减少了175个。因为每一次从WAITING到RUNNING都会进行一次上下文切换。
也可以使用vmstat命令测试
```

- Q: 避免死锁的方法？
- A: (1)避免一个线程同时获取多个锁；(2)避免一个线程在锁内同时占用多个资源，尽量保证每个锁只占用一个资源；(3)尝试使用定时锁，使用lock.tryLock(timeout)来替代使用内部锁机制；（4）对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。

