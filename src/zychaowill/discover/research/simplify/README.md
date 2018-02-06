- Run ReentrantLockExpression to get result as follows:

```
[tryReentrantLock] begin to execute, name is A1, time is 1517880806747.
[tryReentrantLock] end to execute, name is A1, time is 1517880811754.
[tryReentrantLockAgain] begin to execute, name is B1, time is 1517880811754.
[tryReentrantLockAgain] end to execute, name is B1, time is 1517880816755.
[tryReentrantLockAgain] begin to execute, name is B2, time is 1517880816755.
[tryReentrantLockAgain] end to execute, name is B2, time is 1517880821756.
[tryReentrantLock] begin to execute, name is A2, time is 1517880821756.
[tryReentrantLock] end to execute, name is A2, time is 1517880826757.
```

- Run ReentrantReadWriteLockExpressionExample to get result as follows:

> 读写、写读和写写都是互斥的；而读读是异步的，非互斥的。

```
2018-02-06 12:21:09,864  INFO [A] (ReentrantReadWriteLockExpression.java:28) - [write] A holds write-lock at 1517890869863.
2018-02-06 12:21:12,866  INFO [A] (ReentrantReadWriteLockExpression.java:33) - [write] A releases write-lock at 1517890872866.
2018-02-06 12:21:12,867  INFO [B] (ReentrantReadWriteLockExpression.java:15) - [read] B holds read-lock at 1517890872867.
2018-02-06 12:21:15,868  INFO [B] (ReentrantReadWriteLockExpression.java:20) - [read] B releases read-lock at 1517890875868.
```
