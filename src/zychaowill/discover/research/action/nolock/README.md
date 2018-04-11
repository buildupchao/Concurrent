#### 有助于提高“锁”性能的几点建议：
“锁”的竞争必然会导致程序的整体性能下降。为了将这种副作用降到最低，有一些关于使用锁的建议：

- 减小锁持有时间
只在必要时进行同步，这样就能明显减少线程持有锁的时间，提高系统的吞吐量。
```Java
public void syncMethod() {
	otherCode();
	synchronized (this) {
		mutexMethod();
	}
}
```