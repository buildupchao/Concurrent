package com.buildupchao.concurrent.disruptor.simple;

import com.buildupchao.concurrent.disruptor.simple.handler.*;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author buildupchao
 * @date 2019/3/16
 * @since JDK 1.8
 */
public class DisruptorExamples {


    @Test
    public void testComplexCombineSequence() {
        ExecutorService executor = Executors.newFixedThreadPool(20);
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
