<h4>就重入锁的实现来看，它主要集中在Java层面。在重入锁的实现中，主要包含三个要素：<h4/>

- 原子状态。原子状态使用CAS操作来粗UC户当前锁的状态，判断所是否已经被别的线程持有。

- 等待队列。所有没有请求到锁的线程，会进入等待队列进行等待。待有线程释放锁后，系统就能从等待队列中唤醒一个线程，继续工作。

- 阻塞原语part()和unpark()，用来挂起和恢复线程。没有得到锁的线程将会被挂起。

<h4>CountDownLatch</h4>

- 如果出现await超时等待或者执行任务线程抛异常，均会返回false; 只有当任务无超时正常调用countDown()方法，await才会返回true