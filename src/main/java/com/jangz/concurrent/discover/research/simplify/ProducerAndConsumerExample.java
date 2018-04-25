package com.jangz.concurrent.discover.research.simplify;

import lombok.AllArgsConstructor;

public class ProducerAndConsumerExample {

	public static void main(String[] args) {
		final ProducerAndConsumer producerAndConsumer = new ProducerAndConsumer();

		ProducerThread[] producers = new ProducerThread[10];
		ConsumerThread[] consumers = new ConsumerThread[10];
		for (int i = 0; i < 10; i++) {
			producers[i] = new ProducerThread(producerAndConsumer);
			consumers[i] = new ConsumerThread(producerAndConsumer);
			producers[i].start();
			consumers[i].start();
		}
	}
	
	@AllArgsConstructor
	static class ProducerThread extends Thread {
		private ProducerAndConsumer producer;
		
		@Override
		public void run() {
			producer.produce();
		}
	}
	
	@AllArgsConstructor
	static class ConsumerThread extends Thread {
		private ProducerAndConsumer consumer;
		
		@Override
		public void run() {
			consumer.consume();
		}
	}
}
