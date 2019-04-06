package com.jangz.concurrent.disruptor.simple.handler;

import com.jangz.concurrent.disruptor.simple.event.LongEvent;
import com.lmax.disruptor.EventHandler;

/**
 * 事件消费者（事件处理器）
 * @author buildupchao
 * @date 2019/1/9
 */
public class LongEventHandler implements EventHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("Consumer: " + event.getValue());
    }
}
