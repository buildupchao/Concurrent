package com.jangz.concurrent.disruptor.simple.handler;

import com.jangz.concurrent.disruptor.simple.event.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * @author buildupchao
 *         Date: 2019/3/16 03:47
 * @since JDK 1.8
 */
public class ForthEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long l, boolean b) throws Exception {
        event.setValue(event.getValue() * 4);
    }
}
