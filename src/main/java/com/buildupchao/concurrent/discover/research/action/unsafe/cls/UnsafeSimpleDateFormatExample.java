package com.buildupchao.concurrent.discover.research.action.unsafe.cls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
public class UnsafeSimpleDateFormatExample {
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executors = Executors.newFixedThreadPool(500);
		for (int i = 0; i < 500; i++) {
			executors.execute(() -> {
				for (int k = 0; k < 1_000_000; k++) {
					try {
						DATE_FORMAT.parse("2018-06-19 00:00:00");
					} catch (ParseException ex) {
						ex.printStackTrace();
					}
				}
			});
		}
		Thread.sleep(3_000_000);
	}
}
