package com.buildupchao.concurrent.discover.research.simplify;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	public boolean add(E e) {
		if (offer(e))
			return true;
		else
			throw new IllegalStateException("Queue Full");
	}
	
	/**
	 * @throws NullPointerException if the specified element is null 
	 */
	public boolean offer(E e) {
		checkNonNull(e);
		final ReentrantLock lock = this.lock;
		lock.lock();
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
	
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		checkNonNull(e);
		long nanos = unit.toNanos(timeout);
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (count == items.length) {
				if (nanos <= 0)
					return false;
				nanos = notFull.awaitNanos(nanos);
			}
			enqueue(e);
			return true;
		} finally {
			lock.unlock();
		}
	}
	
	public E poll() throws InterruptedException {
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			return (count == 0) ? null : dequeue();
		} finally {
			lock.unlock();
		}
	}
	
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		long nanos = unit.toNanos(timeout);
		final ReentrantLock lock = this.lock;
		lock.lockInterruptibly();
		try {
			while (count == 0) {
				if (nanos <= 0)
					return null;
				nanos = notEmpty.awaitNanos(nanos);
			}
			return dequeue();
		} finally {
			lock.unlock();
		}
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
			log.info("Put a product. -> " + e);
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
			E x = dequeue();
			log.info("Take a product. -> " + x);
			return x;
		} finally {
			lock.unlock();
		}
	}

	private E dequeue() {
		final Object[] items = this.items;
		@SuppressWarnings("unchecked")
		E e = (E) items[takeIndex];
		items[takeIndex] = null;
		if (++takeIndex == items.length)
			takeIndex = 0;
		count--;
		notFull.signal();
		return e;
	}
	
	public int size() {
		final ReentrantLock lock = this.lock;
		lock.lock();
		try {
			return count;
		} finally {
			lock.unlock();
		}
	}
	
	public E peek() {
		final ReentrantLock lock = this.lock;
		lock.lock();
		try {
			return itemAt(takeIndex);
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	final E itemAt(int i) {
		return (E) items[i];
	}

	private void checkNonNull(E e) {
		if (e == null)
			throw new NullPointerException();
	}
	
	public String joining() {
		final Object[] items = this.items;
		final ReentrantLock lock = this.lock;
		lock.lock();
		try {
			return "["
					+ Arrays.stream(items).filter(Objects::nonNull).map(String::valueOf).collect(Collectors.joining(","))
					+ "]";
		} finally {
			lock.unlock();
		}
	}
}
