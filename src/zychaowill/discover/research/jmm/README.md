volatile对于保证操作的原子性是有非常大的帮助的。但是需要注意的是，volatile并不能代替锁，它也无法保证一些复合操作的原子性。
<br/>
volatile也能保证数据的可见性和有序性。<br/>
[在虚拟机的Client模式下，JIT并没有做足够的优化。但是在Server模式下，由于系统优化的结果……]<br/>
可以使用Java虚拟机参数 -server切换到Server模式<br/>