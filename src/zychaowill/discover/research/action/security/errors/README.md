#### 程序中的幽灵：隐蔽的错误

- NoTipErrorExample
```
	v1=1073741827
	v2=1431655768
	average of 1073741827, 1431655768 is -894784850
```


- ArrayListBasedOnConcurrentExample
```
Exception in thread "Thread-0" java.lang.ArrayIndexOutOfBoundsException: 163
	at java.util.ArrayList.add(ArrayList.java:459)
	at zychaowill.discover.research.action.security.errors.ArrayListBasedOnConcurrentExample$AddThread.run(ArrayListBasedOnConcurrentExample.java:13)
	at java.lang.Thread.run(Thread.java:745)
1000136
```