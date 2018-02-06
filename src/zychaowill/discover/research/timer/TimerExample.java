package zychaowill.discover.research.timer;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimerExample {
	
	public static void main(String[] args) {
//		singleTask();
		multiTask();
	}
	
	static class MyTask extends TimerTask {

		@Override
		public void run() {
			log.info("[MyTask#run] task executes at {}.", Timestamp.from(Instant.now()));
		}
		
	}
	
	private static void singleTask() {
		log.info("Current time is {}", Timestamp.from(Instant.now()));
		Calendar calendarRef = Calendar.getInstance();
		calendarRef.add(Calendar.SECOND, 10);
		Date runDate = calendarRef.getTime();
		
		Timer timer = new Timer(false);
		timer.schedule(new MyTask(), runDate);
	}
	
	private static void multiTask() {
		log.info("Current time is {}", Timestamp.from(Instant.now()));
		
		Timestamp runDate = Timestamp.from(Instant.now().plusSeconds(10));
		log.info("Plan A will execute at time {}.", runDate);
		
		Timestamp runDate2 = Timestamp.from(Instant.now().plusSeconds(15));
		log.info("Plan B will execute at time {}.", runDate2);
		
		Timer timer = new Timer(false);
		timer.schedule(new MyTask(), runDate);
		timer.schedule(new MyTask(), runDate2);
	}
}
