package com.buildupchao.concurrent.collection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author buildupchao
 * @date 2019/11/28 06:17
 * @since JDK 1.8
 */
public class ConcurrentHashMapIteratorTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentHashMapIteratorTests.class);

    @Test
    public void testMapIterator() throws Exception {
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "11111");
        map.put(2, "22222");
        map.put(3, "33333");
        map.put(4, "44444");
        map.put(5, "55555");
        map.put(7, "77777");
        map.put(8, "88888");
        new Thread(() -> {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            LOGGER.info("start insert data={} into map", 6);

            map.put(6, "666");

            LOGGER.info("end insert data={} into map", 6);
        }).start();

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getValue());
            Thread.sleep(1000);
        }
    }
}
