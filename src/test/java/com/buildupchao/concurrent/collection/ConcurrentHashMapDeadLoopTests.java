package com.buildupchao.concurrent.collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author buildupchao
 * @date 2019/11/28 06:31
 * @since JDK 1.8
 */
public class ConcurrentHashMapDeadLoopTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapDeadLoopTests.class);

    /**
     * 以下代码会导致死循环，需要注意
     * @throws Exception
     */
    @Test
    public void testConcurrentHashMapDeadLoop() throws Exception {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.computeIfAbsent(12, (k) -> {
            map.put(k, k);
            return k;
        });
        System.out.println(map);
    }
}
