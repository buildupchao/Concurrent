package com.buildupchao.concurrent.discover.research.action.net;

/**
 * @author buildupchao
 *         Date: 2019/3/17 19:23
 * @since JDK 1.8
 */
public class ClientApplication {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new ClientConnector(Constants.host, Constants.port));
            thread.setName("client-connector");
            thread.start();
        }
    }
}
