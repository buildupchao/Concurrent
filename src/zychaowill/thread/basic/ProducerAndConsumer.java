package zychaowill.thread.basic;

public class ProducerAndConsumer {
	
	private Object LOCK = new Object(); 
	private static int counter = 0;
	
	public void produce() {
		new Thread(() -> {
			synchronized(LOCK) {
				while(true) {
					try {
						if (counter == 5) {
							LOCK.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					++counter;
					System.out.println(new StringBuilder("生产一个产品, counter is ").append(counter));
					
					LOCK.notifyAll();
				}
			}
		}).start();
	}
	
	public void consume() {
		new Thread(() -> {
			synchronized(LOCK) {
				while (true) {
					try {
						if (counter == 0) {
							LOCK.wait();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					--counter;
					System.out.println(new StringBuilder("消费一个产品, counter is ").append(counter));
					
					LOCK.notifyAll();
				}
			}
		}).start();
	}
	
//	@Test
//	public void ProducerAndConsumerTest() {
//		produce();
//		produce();
//		consume();
//	}
	
	public static void main(String[] args) {
		ProducerAndConsumer pac = new ProducerAndConsumer();
		pac.produce();
		pac.produce();
		pac.consume();
	}
}
