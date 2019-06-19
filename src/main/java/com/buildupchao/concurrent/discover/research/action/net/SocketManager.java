package com.buildupchao.concurrent.discover.research.action.net;

import com.google.common.collect.Queues;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author buildupchao
 *         Date: 2019/3/17 19:08
 * @since JDK 1.8
 */
public class SocketManager {

    private static final ArrayBlockingQueue<Socket> socketQueue = new ArrayBlockingQueue<Socket>(10);
    private static final ExecutorService SOCKET_POOL = new ThreadPoolExecutor(
            50,
            100,
            0L,
            TimeUnit.SECONDS,
            Queues.newArrayBlockingQueue(10),
            new BasicThreadFactory.Builder().namingPattern("socket-%d").build(),
            new ThreadPoolExecutor.AbortPolicy()
    );
    private volatile static boolean stop = false;

    public static void startSocketManager() {
        startSocketConsumer();
    }

    public static void pushSocket(Socket socket) {
        try {
            socketQueue.put(socket);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void startSocketConsumer() {
        SocketConsumer socketConsumer = new SocketConsumer();
        socketConsumer.setName("socket-consumer");
        socketConsumer.start();
    }

    static class SocketConsumer extends Thread {

        @Override
        public void run() {
            while (true) {
                if (stop) {
                    continue;
                }
                try {
                    Socket socket = socketQueue.take();
                    SOCKET_POOL.execute(new ServerConnector(socket));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
