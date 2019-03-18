package com.jangz.concurrent.discover.research.action.net;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Queues;

/**
 * @author buildupchao
 *         Date: 2019/3/17 19:07
 * @since JDK 1.8
 */
public class ServerAcceptor implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerAcceptor.class);

    private static final ExecutorService SOCKET_SUBMIT_POOL = new ThreadPoolExecutor(
      50,
            100,
            0,
            TimeUnit.SECONDS,
            Queues.newArrayBlockingQueue(10),
            new BasicThreadFactory.Builder().namingPattern("socket-submit-%d").build(),
            new ThreadPoolExecutor.AbortPolicy()
    );
    private int port;
    private volatile boolean stop = false;

    public ServerAcceptor(int port) {
        this.port = port;
    }

    @Override
    public void run() {
    	ServerSocket acceptor = null;
        try {
            acceptor = new ServerSocket(port);
            LOGGER.info("server acceptor starts, listens to port=[{}]", port);

//            int i = 0;
            while (true) {
//                if (i == 5) {
//                    Thread.sleep(5000);
//                    throw new RuntimeException("generate an error!");
//                }

                final Socket socket = acceptor.accept();

                SOCKET_SUBMIT_POOL.execute(() -> {
                    SocketManager.pushSocket(socket);
                });
//                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	IOUtils.closeQuietly(acceptor);
            LOGGER.info("server acceptor exits!");
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
