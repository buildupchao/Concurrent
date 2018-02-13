package zychaowill.discover.research.simplify;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PCBlockingQueue<E> implements Serializable {

	private static final long serialVersionUID = -2471645308014698465L;
	
	final Object[] items;
	int takeIndex;
	int putIndex;
	int count;
	
	final ReentrantLock lock;
	private final Condition notEmpty;
	private final Condition notFull;
	
	public PCBlockingQueue(int capacity) {
		this(capacity, false);
	}
	
	public PCBlockingQueue(int capacity, boolean fair) {
		if (capacity <= 0)
			throw new IllegalArgumentException();
		this.items = new Object[capacity];
		lock = new ReentrantLock(fair);
		notEmpty = lock.newCondition();
		notFull = lock.newCondition();
	}
	
	public PCBlockingQueue(int capacity, boolean fair, Collection<? extends E> c) {
		this(capacity, fair);
		
		final ReentrantLock lock = this.lock;
		lock.lock();
		try {
			int i = 0;
			try {
				for (E e : c) {
					checkNonNull(e);
					items[i++] = e;
				}
			} catch (IllegalArgumentException e) {
				throw new IllegalArgumentException();
			}
			count = i;
			putIndex = (i == capacity) ? 0 : i;
		} finally {
			lock.unlock();
		}
	}

	public boolean offer(E e) throws InterruptedException {
		checkNonNull(e);
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			if (count == items.length)
				return false;
			else {
				enqueue(e);
				return true;
			}
		} finally {
			lock.unlock();
		}
	}
	
	public boolean offer(E e, TimeUnit unit) {
		checkNonNull(e);
		final ReentrantLock lock = this.lock;
		
		return false;
	}
	
	/**
	 * Inserts the specified element at the tail of this queue, waiting
	 * for space to become available if the queue is full.
	 * 
	 * @throws InterruptedException 
	 *  
	 */
	public void put(E e) throws InterruptedException {
		checkNonNull(e);
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (count == items.length)
				notFull.await();
			enqueue(e);
		} finally {
			lock.unlock();
		}
	}
	
	private void enqueue(E e) {
		final Object[] items = this.items;
		items[putIndex] = e;
		if (++putIndex == items.length)
			putIndex = 0;
		count++;
		notEmpty.signal();
	}
	
	public E take() throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (count == 0)
				notEmpty.await();
			return dequeue();
		} finally {
			lock.unlock();
		}
	}

	private E dequeue() {
		final Object[] items = this.items;
		@SuppressWarnings("unchecked")
		E e = (E) items[takeIndex];
		items[takeIndex] = null;
		if (takeIndex == items.length)
			takeIndex = 0;
		count--;
		notFull.signal();
		return e;
	}
	
	public int size() throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}

	private void checkNonNull(E e) {
		if (e == null)
			throw new NullPointerException();
	}
}
