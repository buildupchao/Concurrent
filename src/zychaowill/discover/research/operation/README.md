【强制】 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方法，这样的处理方式让写的同学更加明确线程池的运行规则，避免资源耗尽的风险。<br/>
【说明】Executors 返回的线程池对象的弊端如下：
1）FixedThreadPool 和 SingleThreadPool
允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM。
2）CachedThreadPool 和 ScheduledThreadPool
允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM。
<br/><br/>
【强制】 SimpleDateFormat 是线程不安全的类，一般不要定义为static变量，如果定义为static，必须加锁，或者使用 DateUtils工具类。<br/>
[正例]注意线程安全，使用DateUtils。亦推荐如下处理：
```Java
private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
	@Override
	protected DateFormat initialValue() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}
}
```
<br/>
【说明】 如果是JDK8的应用，可以使用 Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替SimpleDateFormat，官方给出的解释：simple beautiful strong immutable thread-safe。
<br/><br/>
【强制】高并发时，同步调用应该考量锁的性能损耗。能用无锁数据结构，就不要用锁；能锁区块，就不要锁整个方法体；能用对象锁，就不要用类锁。<br/>
【说明】尽可能是加锁的代码块工作量尽可能的小，避免在锁代码块中调用RPC方法。
<br/><br/>
【强制】对多个资源、数据库表、对象同时加锁时，需要保持一致的加锁顺序，否则可能会造成死锁。
<br/><br/>
【强制】并发修改同一记录时，避免更新丢失，需要加锁。要么在应用层加锁，要么在缓存加锁，要么在数据库层使用乐观锁，使用version作为更新依据。<br/>
【说明】如果每次访问冲突概率小于20%，推荐使用乐观锁，否则使用悲观锁。乐观锁的重试次数不得小于3次。
<br/><br/>
【强制】多线程并行处理定时任务时，Timer运行多个TimeTask时，只要其中之一没有捕获抛出的异常，其它任务便会自动终止运行，使用ScheduledExecutorService则没有这个问题。
<br/><br/>
【推荐】使用CountDownLatch进行异步转同步操作，每个额线程退出前必须调用countDown方法，线程执行代码注意catch异常，确保countDown方法被执行到，避免主线程无法执行至await方法，知道超时才返回结果。<br/>
【说明】注意，子线程抛出异常堆栈，不能在主线程try-catch到。
<br/><br/>
【推荐】避免Random实例被多线程使用，虽然共享该实例是线程安全的，但会因竞争同一seed导致的性能下降。<br/>
【说明】Random实例包括java.util.Random的实例或者Math.random()的方式。<br/>
【正例】在JDK7之后，可以直接使用API ThreadLocalRandom，而在JDK7之前，需要编码保证每个线程持有一个实例。
<br/><br/>
【推荐】在并发场景下，通过双重检查所（double-checked locking）实现延迟初始化的优化问题隐患（可参考The "Double-Checked Locking is Broken" Declaration），推荐解决方案中较为简单一种（使用与JDK5及以上版本），将目标属性声明为 volatile型。<br/>
【反例】
```Java
class Singleton {
	private Helper helper = null;
	public Helper getHelper() {
		if (helper == null) {
			synchronized (this) {
				if (helper == null) {
					helper = new Helper();
				}
			}
		}
		return helper;
	}
	// other methods and fields
}
```
<br/><br/>
【参考】volatile解决多线程内存不可见问题。对于一写多读，是可以解决变量同步问题，但是如果多写，同样无法解决线程安全问题。如果是count++操作，使用如下类实现：<br/>
```Java
AtomicInteger count = new AtomicInteger();
count.addAndGet(1);
```
<br/>
如果是JDK8，推荐使用LongAdder对象，比AtomicLong性能更好（减少乐观锁的重试次数）。
<br/><br/>
【参考】ThreadLocal无法解决共享对象的更新问题，ThreadLocal对象建议使用static修饰。这个变量是针对一个线程内所有操作共享的，所以设置为静态变量，所有此类实例共享此静态变量，也就是说在类第一次被使用时装载，只分配一块存储空间，所有此类的对象（只要是这个线程内定义的）都可以操作这个变量。
<br/><br/>