package com.buildupchao.concurrent.disruptor.simple.factory;

import com.buildupchao.concurrent.disruptor.simple.event.LongEvent;
import com.lmax.disruptor.EventFactory;

/**
 * 因为需要Disruptor创建事件，需要我们声明一个EventFactory来实例化Event。
 * @author buildupchao
 * @date 2019/1/9
 */
public class LongEventFactory implements EventFactory<LongEvent> {

    @Override
    public LongEvent newInstance() {
        return new LongEvent();
    }
}
