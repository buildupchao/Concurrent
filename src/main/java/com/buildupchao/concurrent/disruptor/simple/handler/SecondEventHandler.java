package com.buildupchao.concurrent.disruptor.simple.handler;

import com.buildupchao.concurrent.disruptor.simple.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author buildupchao
 * @date 2019/3/16
 * @since JDK 1.8
 */
public class SecondEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        event.setValue(event.getValue() / 2);
    }
}
