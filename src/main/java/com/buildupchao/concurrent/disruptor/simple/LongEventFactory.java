package com.buildupchao.concurrent.disruptor.simple;

import com.lmax.disruptor.EventFactory;

/**
 * Created by jangz on 2019/1/9.
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
