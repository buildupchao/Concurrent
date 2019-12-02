package com.buildupchao.concurrent.discover.research.action.unsafe.datastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *    ConcurrentHashMap死循环案例
 * </p>
 * @author buildupchao
 * @date 2019/12/02 14:37
 * @since JDK 1.8
 */
public class ConcurrentHashMapDeadLoopExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapDeadLoopExample.class);

    /**
     * 以下代码会导致死循环，需要注意
     * @throws Exception
     */
    public void testConcurrentHashMapDeadLoop() throws Exception {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        map.computeIfAbsent(12, (k) -> {
            LOGGER.info("put element {}-{}", k, k);
            map.put(k, k); // will generate dead loop and result in high CPU.
            LOGGER.info("put element {}-{} successfully", k, k);
            return k;
        });
        LOGGER.info("prepare to print map");
        System.out.println(map);
    }

    public static void main(String[] args) throws Exception {
        new ConcurrentHashMapDeadLoopExample().testConcurrentHashMapDeadLoop();
    }
}
