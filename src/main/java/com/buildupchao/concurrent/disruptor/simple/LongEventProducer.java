package com.buildupchao.concurrent.disruptor.simple;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * @author buildupchao
 * @date 2019/1/9
 * @since JDK 1.8
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        Long data = null;

        try {
            LongEvent longEvent = ringBuffer.get(sequence);
            data = byteBuffer.getLong(0);

            longEvent.setValue(data);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            System.out.println("Producer准备发送数据" + data);
            ringBuffer.publish(sequence);
        }
    }
}
