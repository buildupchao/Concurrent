package com.jangz.concurrent.disruptor.simple;

import com.jangz.concurrent.disruptor.simple.event.LongEvent;
import com.jangz.concurrent.disruptor.simple.factory.LongEventFactory;
import com.jangz.concurrent.disruptor.simple.handler.LongEventHandler;
import com.jangz.concurrent.disruptor.simple.producer.LongEventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.*;

/**
 * @author buildupchao
 * @date 2019/1/9
 */
public class LongEventMain {

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                20,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );
        LongEventFactory eventFactory = new LongEventFactory();
        int ringBufferSize = 1024 * 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, executor,
                ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith(new LongEventHandler());
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 1; i <= 100; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }

        disruptor.shutdown();
        executor.shutdown();
    }
}
