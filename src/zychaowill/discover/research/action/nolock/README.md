### 锁的优化及注意事项

#### 1 有助于提高"锁"性能的几点建议
##### 1.1 减小锁持有时间
对于使用锁进行并发控制的应用程序而言，在锁竞争过程中，单个线程对锁的持有时间与系统性能有着直接的关系。
<br/>
程序开发也是类似的，应该尽可能地减少对某个锁的占有时间，以减少线程间互斥的可能。
<br/>
<br/>
- 一个较为优化的解决方案
> 只在必要时进行同步，这样就能明显减少线程持有锁的时间，提高系统的吞吐量。


e g: JDK的源码包中也可以很容易地找到，比如处理正则表达式的Pattern类：
```Java
public Matcher matcher(CharSequence input) {
    if (!compiled) {
        synchronized (this) {
            if (!compiled) {
                compile();
            }
        }
    }
    Matcher m = new Matcher(this, input);
    return m;
}
```
<br/>
<h5>注意：减少锁的持有时间有助于降低锁冲突的可能性，进而提升系统的并发能力。</h5>

##### 1.2 减小锁粒度
