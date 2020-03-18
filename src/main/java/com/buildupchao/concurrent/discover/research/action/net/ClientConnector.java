package com.buildupchao.concurrent.discover.research.action.net;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author buildupchao
 * @date 2019/3/17 19:01
 * @since JDK 1.8
 */
public class ClientConnector implements Runnable {

    private String host;
    private int port;

    private volatile boolean stop = false;

    public ClientConnector(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        Socket socket = null;
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String line = null;
            while (true) {
                if (stop) {
                    break;
                }
                writer.write("hello server!");
                writer.newLine();
                writer.flush();

                if ((line = reader.readLine()) != null) {
                    System.out.println("get message from server: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(reader);
            IOUtils.closeQuietly(socket);
        }
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }
}
