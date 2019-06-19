package com.buildupchao.concurrent.disruptor.simple;

import com.buildupchao.concurrent.disruptor.simple.event.LongEvent;
import com.buildupchao.concurrent.disruptor.simple.producer.LongEventProducerWithTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author buildupchao
 * @date: 2019/4/6 15:46
 * @since JDK 1.8
 */
public class LongEventMainWithTranslator {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
          10,
                10,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );

        int ringBufferSize = 1024;
        Disruptor<LongEvent> disruptor =
                new Disruptor<LongEvent>(LongEvent::new, ringBufferSize, executor, ProducerType.SINGLE, new YieldingWaitStrategy());
        disruptor.handleEventsWith((LongEvent event, long sequence, boolean endOfBatch) -> {
            System.out.printf("Got value=[%d]\n", event.getValue());
        });
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducerWithTranslator producer =
                new LongEventProducerWithTranslator(ringBuffer);

        int MAX_MESSAGE_NUM = 100;
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (int i = 0; i < MAX_MESSAGE_NUM; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
            Thread.sleep(100);
        }

        executor.shutdown();
        disruptor.shutdown();
    }
}
