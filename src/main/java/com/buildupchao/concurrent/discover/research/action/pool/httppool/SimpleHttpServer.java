package com.buildupchao.concurrent.discover.research.action.pool.httppool;

import com.buildupchao.concurrent.discover.research.action.pool.threadpool.DefaultSimpleThreadPool;
import com.buildupchao.concurrent.discover.research.action.pool.threadpool.SimpleThreadPool;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author buildupchao
 * @date 2019/9/14 23:20
 * @since JDK 1.8
 */
public class SimpleHttpServer {

    /**
     * 处理HttpRequest的线程池
     */
    static SimpleThreadPool<HttpRequestHandler> threadPool = new DefaultSimpleThreadPool<HttpRequestHandler>();

    /**
     * SimpleHttpServer的根路径
     */
    static String basePath;

    static ServerSocket serverSocket;

    static int port = 8080;

    public static void setPort(int port) {
        if (port > 0) {
            SimpleHttpServer.port = port;
        }
    }

    public static void setBasePath(String basePath) {
        if (basePath != null) {
            boolean satisfied = Files.exists(Paths.get(basePath)) && Files.isDirectory(Paths.get(basePath));
            if (satisfied) {
                SimpleHttpServer.basePath = basePath;
            }
        }
    }

    public static void start() throws Exception {
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while ((socket = serverSocket.accept()) != null) {
            // 接收一个客户端Socket，生成一个HttpRequestHandler，放入线程池执行
            threadPool.execute(new HttpRequestHandler(socket));
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {

        private Socket socket;

        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;

            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String header = reader.readLine();
                // 由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
                out = new PrintWriter(socket.getOutputStream());
                // 如果请求资源的后缀为jpg或者ico，则读取资源并输出
                if (filePath.endsWith("jps") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
