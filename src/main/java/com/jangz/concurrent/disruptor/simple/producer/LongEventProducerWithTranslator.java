package com.jangz.concurrent.disruptor.simple.producer;

import com.jangz.concurrent.disruptor.simple.event.LongEvent;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author buildupchao
 * @date: 2019/4/6 15:29
 * @since JDK 1.8
 */
public class LongEventProducerWithTranslator {

    private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
            new EventTranslatorOneArg<LongEvent, ByteBuffer>() {
                @Override
                public void translateTo(LongEvent event, long sequence, ByteBuffer byteBuffer) {
                    event.setValue(byteBuffer.getLong(0));
                }
            };

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        ringBuffer.publishEvent(TRANSLATOR, byteBuffer);
    }
}
