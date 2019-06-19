package com.buildupchao.concurrent.disruptor.simple.producer;

import com.buildupchao.concurrent.disruptor.simple.event.LongEvent;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

/**
 * 事件都会有一个生成事件的源，事件源通过一个ByteBuffer来模拟它接收到的数据。
 * 该案例假设事件是由于磁盘IO或Network读取数据的时候触发的，也就是说，事件源会在IO读取到一部分数据的时候
 * 触发事件（触发事件不是自动的，需要在读取到数据的时候，程序员自己触发事件并发布）。
 * @author buildupchao
 * @date 2019/1/9
 */
public class LongEventProducer {

    private final RingBuffer<LongEvent> ringBuffer;

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /**
     * 用来发布事件。
     * 它的参数会通过事件传递给消费者。
     * @param byteBuffer
     */
    public void onData(ByteBuffer byteBuffer) {
        // 可以把RingBuffer看做一个事件队列，next是得到下一个事件槽。
        long sequence = ringBuffer.next();
        Long data = null;

        /**
         * 通过try-finally保证事件一定会发布。
         * 如果不能发布事件，会引起Disruptor状态的混乱。尤其是在多个事件生产者的情况下会导致事件消费者失速，
         * 从而不得不重启应用才能恢复。
         */
        try {
            // 用上面的索引取出来一个空的事件用于填充
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
            // 发布事件
            /**
             * Disruptor 3.0提供了lambda式的API。这样可以把一些复杂的操作放到Ring Buffer，
             * 所以在Disruptor 3.0以后的版本最好使用Event Publisher或者Event Translator来发布事件。
             */
            ringBuffer.publish(sequence);
        }
    }
}
