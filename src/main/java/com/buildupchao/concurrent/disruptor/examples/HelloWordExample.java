package com.buildupchao.concurrent.disruptor.examples;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import lombok.Getter;
import lombok.Setter;

/**
 * @author buildupchao
 * @date 2019/4/6 14:53
 * @since JDK 1.8
 */
public class HelloWordExample {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		int ringBufferSize = 8;
		ExecutorService executor = Executors.newFixedThreadPool(16);
		Disruptor<HelloWorldWrapper> disruptor = new Disruptor<>(new EventFactory<HelloWorldWrapper>() {
			@Override
			public HelloWorldWrapper newInstance() {
				return new HelloWorldWrapper();
			}
		}, ringBufferSize, executor);
		
		disruptor.handleEventsWith(new EventHandler<HelloWorldWrapper>() {
			@Override
			public void onEvent(HelloWorldWrapper event, long sequence, boolean endOfBatch) throws Exception {
				System.out.println(event.getMsg());
			}
		});
		
		disruptor.start();
		
		RingBuffer<HelloWorldWrapper> ringBuffer = disruptor.getRingBuffer();
		for (int i = 0; i < 20; i++) {
			long sequence = ringBuffer.next();
			HelloWorldWrapper wrapper = ringBuffer.get(sequence);
			wrapper.setMsg(i + ": Hello World");
			ringBuffer.publish(sequence);
		}
		
		disruptor.shutdown();
		executor.shutdown();
	}
	
	
	@Getter
	@Setter
	static class HelloWorldWrapper {
		String msg;
	}
}
