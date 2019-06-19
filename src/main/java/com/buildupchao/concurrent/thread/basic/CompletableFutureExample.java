package com.buildupchao.concurrent.thread.basic;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
