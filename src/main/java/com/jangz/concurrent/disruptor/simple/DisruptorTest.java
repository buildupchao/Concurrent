package com.jangz.concurrent.disruptor.simple;

import com.google.common.collect.Queues;
import com.jangz.concurrent.disruptor.simple.event.LongEvent;
import com.jangz.concurrent.disruptor.simple.factory.LongEventFactory;
import com.jangz.concurrent.disruptor.simple.handler.*;
import com.jangz.concurrent.disruptor.simple.producer.LongEventProducer;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author buildupchao
 *         Date: 2019/3/16 03:40
 * @since JDK 1.8
 */
public class DisruptorTest {


    @Test
    public void testComplexCombineSequence() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                20,
                20,
                0,
                TimeUnit.SECONDS,
                Queues.newArrayBlockingQueue(10),
                new BasicThreadFactory.Builder().namingPattern("disruptor-test-%d").daemon(true).build(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        LongEventFactory eventFactory = new LongEventFactory();
        int bufferSize = 8;

        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, executor, ProducerType.SINGLE, new
                BlockingWaitStrategy());
        FirstEventHandler firstEventHandler = new FirstEventHandler();
        SecondEventHandler secondEventHandler = new SecondEventHandler();
        ThirdEventHandler thirdEventHandler = new ThirdEventHandler();
        ForthEventHandler forthEventHandler = new ForthEventHandler();
        LongEventHandler longEventHandler = new LongEventHandler();

        // (x - 1) / 2
        // (x + 3) * 4
        disruptor.handleEventsWith(firstEventHandler, thirdEventHandler);
        disruptor.after(firstEventHandler).handleEventsWith(secondEventHandler);
        disruptor.after(thirdEventHandler).handleEventsWith(forthEventHandler);
        disruptor.after(secondEventHandler, forthEventHandler).handleEventsWith(longEventHandler);

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducer producer = new LongEventProducer(ringBuffer);
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        for (long i = 0; i < 10L; i++) {
            byteBuffer.putLong(0, i);
            producer.onData(byteBuffer);
        }

        disruptor.shutdown();
    }
}
