package com.jangz.concurrent.disruptor.simple;

import com.lmax.disruptor.EventHandler;

/**
 * Created by jangz on 2019/1/9.
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Consumer: " + event.getValue());
    }
}
