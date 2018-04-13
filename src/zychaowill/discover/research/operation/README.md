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
