#### 死锁的四个必要条件

1.互斥条件<br/>
2.请求与保持条件<br/>
3.不可剥夺条件<br/>
4.循环等待条件<br/>

```
synchronized (x) {
    synchronized (y) {
             
    }
}

```

#### jps
> 通过jps查看进程

#### jstack -l process_id
> 通过jstack查看线程间状态，是否出现死锁