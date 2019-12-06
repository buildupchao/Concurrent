package com.buildupchao.concurrent.discover.research.action.unsafe.datastructure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *     Writing to a Java TreeMap concurrently can lead to an infinite loop during reads.
 *
 *     The java.util.TreeMap documentation warns that the underlying implementation for this Map is not
 *     synchronized - and thus it is not thread-safe.
 *
 *     But have you ever wondered what happens if you ignore that warning and you write an application that
 *     concurrently writes to the map from different threads? Intuitively, I'd probably expect some entries
 *     to be lost, or to sometimes get a NullPointerException.
 *
 *     But did you know that - at least with the current implementation shipped to OpenJDK - you can actually
 *     corrupt the TreeMap in such a way that attempts to read the map will result in your code getting stuck
 *     on an infinite loop?
 *
 *     This happens because the TreeMap is implemented as a red-black tree, a self-balancing tree algorithm.
 *     As new elements are inserted into the tree, the tree is rebalanced-elements are moved around so that
 *     the tree is kept height-balanced.
 *
 *     When several threads are adding new entries to a TreeMap at the same time, those threads also compete
 *     to re-balance the tree concurrently. And because re-balancing entails changing several pointers, the
 *     result is that the tree may form internal loops-thus no longer being a tree.
 *
 *     The following application is able to show this issue. It repeatedly creates a new TreeMap, writes to
 *     it using two different concurrent threads, and then navigates the internal map structure using reflection
 *     to check if the TreeMap formed a loop. When it finds a loop, it prints it.
 *
 *     Note: Because this is a concurrency issue, this code is not always successful in triggering an issue.
 *
 *     On other runs, you may get an infinite loop during a put() operation.
 *
 *     As always, be careful when combining concurrency and mutation. And thanks to my awesome colleague Oli for
 *     the really interesting debugging session.
 * </p>
 * @author buildupchao
 * @date 2019/12/02 16:55
 * @since JDK 1.8
 */
public class ConcurrentTreeMapLoopReproducer {

    public static void main(String[] args) throws Exception {
        System.out.println("Runnint on Java " + System.getProperty("java.version"));
        int insertUpTo = 20;
        if (args.length > 0) {
            insertUpTo = Integer.parseInt(args[0]);
        }
        System.out.println("Using insertUpTo=" + insertUpTo);

        new ConcurrentTreeMapLoopReproducer(insertUpTo).call();
    }

    TreeMap<Integer, Integer> brokenMap = null;
    int insertUpTo;

    ConcurrentTreeMapLoopReproducer(int insertUpTo) {
        this.insertUpTo = insertUpTo;
    }

    static class LoopStep {
        Map.Entry<Integer, Integer> entry;
        String direction;
        LoopStep next;

        LoopStep(Map.Entry<Integer, Integer> entry) {

        }

        LoopStep(Map.Entry<Integer, Integer> entry, String direction, LoopStep next) {
            this.entry = entry;
            this.direction = direction;
            this.next = next;
        }

        @Override
        public String toString() {
            if (next != null) {
                return "" + entry.getKey() + " -- " + direction + "-->" + next;
            } else {
                return entry.getKey().toString();
            }
        }
    }

    public void call() throws Exception {
        LoopStep loopPath = null;
        System.out.println("Attempting to trigger looping TreeMap");
        for (int attempts = 1; loopPath == null; attempts++) {
            System.out.println("_");
            System.out.flush();

            brokenMap = tryToTriggerLoopingTreeMap();
            loopPath = findLoop(brokenMap);
        }

        System.out.println("\nWas able to reproduce loop in map:" + loopPath);
        // Uncomment this to see the loop in action: This will never finish
        // brokenMap.values().toArray();
    }

    static TreeMap<Integer, Integer> tryToTriggerLoopingTreeMap() throws Exception {
        TreeMap<Integer, Integer> map = new TreeMap<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 20; i += 2) {
                try {
                    map.put(i, i);
                } catch (Exception e) {
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 20; i += 2) {
                try {
                    map.put(i, i);
                } catch (Exception e) {
                }
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        return map;
    }

    static LoopStep findLoop(TreeMap<Integer, Integer> map) throws Exception {
        Field rootField = TreeMap.class.getDeclaredField("root");
        rootField.setAccessible(true);

        Map.Entry<Integer, Integer> rootEntry = (Map.Entry<Integer, Integer>) rootField.get(map);
        return findLoopRecursive(new ArrayList<>(), rootEntry);
    }

    static LoopStep findLoopRecursive(List<Map.Entry<Integer, Integer>> currentPath,
                                      Map.Entry<Integer, Integer> entry) throws Exception {
        if (entry == null) {
            return null;
        }

        if (currentPath.contains(entry)) {
            currentPath.add(entry);
            return new LoopStep(entry);
        }

        currentPath.add(entry);

        LoopStep result = null;

        result = findLoopRecursive(currentPath, get("left", entry));
        if (result != null) {
            return new LoopStep(entry, "left", result);
        }
        result = findLoopRecursive(currentPath, get("right", entry));
        if (result != null) {
            return new LoopStep(entry, "right", result);
        }
        currentPath.remove(entry);

        return null;
    }

    static Map.Entry<Integer, Integer> get(String direction, Map.Entry<Integer, Integer> parent) throws Exception {
        Field field = parent.getClass().getDeclaredField(direction);
        field.setAccessible(true);
        return (Map.Entry<Integer, Integer>) field.get(parent);
    }
}
