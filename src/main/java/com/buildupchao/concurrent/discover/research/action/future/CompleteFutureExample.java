package com.buildupchao.concurrent.discover.research.action.future;

import java.util.concurrent.CompletableFuture;

import com.buildupchao.concurrent.utils.PrintlnUtils;

public class CompleteFutureExample {

	public static void main(String[] args) {
//		simple();
		exceptionally();
	}
	
	public static void simple() {
		String result = CompletableFuture.supplyAsync(() -> "hello ")
				.thenCombine(
						CompletableFuture.supplyAsync(() -> "world"), 
						(s1, s2) -> s1 + " " + s2
				).join();
		PrintlnUtils.println(result);
	}
	
	public static void exceptionally() {
		CompletableFuture.supplyAsync(() -> Integer.parseInt("x"))
			.thenApply(r -> r * 2 * Math.PI)
			.thenApply(s -> "apply>>>" + s)
			.exceptionally(ex -> {
				String errorMessage = "Error here!!!>>>" + ex.getMessage();
				PrintlnUtils.println(errorMessage);
				return errorMessage;
			}).join();
			
	}
}
