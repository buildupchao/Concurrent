package com.buildupchao.concurrent.disruptor.simple;

import com.lmax.disruptor.EventFactory;

/**
 * @author buildupchao
 * @date 2019/1/9
 * @since JDK 1.8
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
