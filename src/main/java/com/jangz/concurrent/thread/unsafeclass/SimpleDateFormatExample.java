package com.jangz.concurrent.thread.unsafeclass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleDateFormatExample {

	private static final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>() {
		@Override
		protected DateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newFixedThreadPool(500);
		for (int i = 0; i < 500; i++) {
			executors.execute(() -> {
				for (int k = 0; k < 1_000_000; k++) {
					try {
						df.get().parse("2018-06-19 00:00:00");
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
			});
		}
		Thread.sleep(3_000_000);
	}
}