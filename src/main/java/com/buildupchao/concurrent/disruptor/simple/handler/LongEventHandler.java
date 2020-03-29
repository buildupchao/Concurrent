package com.buildupchao.concurrent.disruptor.simple.handler;

import com.buildupchao.concurrent.disruptor.simple.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author buildupchao
 * @date 2019/1/9
 * @since JDK 1.8
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Consumer: " + event.getValue());
    }
}
