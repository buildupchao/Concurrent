package com.buildupchao.concurrent.discover.deepin.aqs.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author buildupchao
 * @date 2020/04/17 00:27
 * @since JDK 1.8
 */
public class Cache {
    static Map<String, Object> map = new HashMap<>();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock r = readWriteLock.readLock();
    static Lock w = readWriteLock.writeLock();


    public static final Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    public static final Object put(String key, Object value) {
        w.lock();
        try {
            return map.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static final void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}
