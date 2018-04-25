#### synchronized(非this对象x)格式的写法是将x对象本身作为“对象监视器”，这样可以得出以下3个结论：

1) 当多个线程同时执行synchronized(x){}同步代码块时呈同步效果

2) 当其他线程执行x对象中synchronized同步方法时呈同步效果

3) 当其他线程中执行x对象方法里面的synchronized(this)代码块时也呈现同步效果

但需要注意的是：如果其他线程调用不加synchronized关键字的方法时，还是异步调用。