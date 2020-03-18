package com.buildupchao.concurrent.discover.smallskill;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author buildupchao
 * @date 2017/8/17
 */
public class CompletableFutureExample {

	public static void main(String[] args) {
		callInStream();
	}
	
	public static void callInStream() {
		CompletableFuture<Void> future = CompletableFuture
			.supplyAsync(() -> calculate(50))
			.thenApply((i) -> Integer.valueOf(i))
			.thenApply((str) -> "\"" + str + "\"")
			.thenAccept(System.out::println);
		try {
			future.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	private static Integer calculate(Integer value) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
		}
		return value * value;
	}
}
