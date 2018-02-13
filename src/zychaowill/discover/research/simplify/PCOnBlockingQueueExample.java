package zychaowill.discover.research.simplify;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PCOnBlockingQueueExample {

	public static void main(String[] args) {
		start();
		
//		ArrayBlockingQueue
		
		end();
	}
	
	private static void start() {
		log.info("Start producer and consumer based on blocking queue.");		
	}
	
	private static void end() {
		log.info("End producer and consumer based on blocking queue.");		
	}
}
