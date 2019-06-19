package com.buildupchao.concurrent.disruptor.simple;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author buildupchao
 * @date: 2019/4/6 15:56
 * @since JDK 1.8
 */
public class LongEventMainUsingLambda {

    private static final int MAX_EVENT_NUM = 20;

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                10,
                0,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.AbortPolicy()
        );

        int ringBufferSize = 2;
        Disruptor<UserInfo> disruptor = new Disruptor<>(UserInfo::new,
                ringBufferSize,
                executor,
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        disruptor.handleEventsWith((UserInfo user, long sequence, boolean endOfBatch) -> {
            System.out.println(user.toString());
        });
        disruptor.start();

        RingBuffer<UserInfo> ringBuffer = disruptor.getRingBuffer();
        UserInfo userInfo = new UserInfo();
        for (int i = 0; i < MAX_EVENT_NUM; i++) {
            userInfo.setUserId(i);
            userInfo.setUserName("user" + i);
            userInfo.setPassword(UUID.randomUUID().toString());

            ringBuffer.publishEvent((event, sequence, user) -> {
                BeanUtils.copyProperties(user, event);
            }, userInfo);
            Thread.sleep(200);
        }

        executor.shutdown();
        disruptor.shutdown();
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class UserInfo {
        private int userId;
        private String userName;
        private String password;
    }
}
