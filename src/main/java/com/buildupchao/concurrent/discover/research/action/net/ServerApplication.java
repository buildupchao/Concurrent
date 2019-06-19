package com.buildupchao.concurrent.discover.research.action.net;

/**
 * @author buildupchao
 *         Date: 2019/3/17 19:22
 * @since JDK 1.8
 */
public class ServerApplication {

    public static void main(String[] args) {
        SocketManager.startSocketManager();

        Thread thread = new Thread(new ServerAcceptor(Constants.port));
        thread.setName("server-acceptor");
        thread.start();
    }
}
