package com.buildupchao.concurrent.discover.research.action.net;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;

/**
 * @author buildupchao
 * @date 2019/3/17 19:13
 * @since JDK 1.8
 */
public class ServerConnector implements Runnable {

    private Socket socket;
    private volatile boolean stop = false;

    public ServerConnector(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedWriter writer = null;
        BufferedReader reader = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;
            while (true) {
                if (stop) {
                    break;
                }
                if ((line = reader.readLine()) != null) {
                    System.out.println("get message from client: " + line);
                }

                writer.write("hello client!");
                writer.newLine();
                writer.flush();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
