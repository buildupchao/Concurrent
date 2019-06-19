package com.buildupchao.concurrent.discover.research.action.inlock;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.buildupchao.concurrent.discover.research.domain.User;

public class CountDownLatchNewExample {

	private List<User> getUsersFromRemote() {
		try {
			Thread.sleep(10000);
			return Arrays.asList(User.of("jangz", "password1"), User.of("Zychaowill", "password2"),
					User.of("Forward", "password3"));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Boolean exists(String userName) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return Arrays.asList("jangz", "Zychaowill", "Forward").contains(userName);
	}
	
	private Boolean check(User user) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return (user != null && user.validate());
	}
	
	public void executeBasedOnThread() {
		ExecutorService executors = Executors.newFixedThreadPool(3);
		Future<Boolean> task1 = executors.submit(() -> check(User.of("jangz", "123456")));
		Future<Boolean> task2 = executors.submit(() -> exists("Fly"));
		Future<List<User>> task3 = executors.submit(() -> getUsersFromRemote());
		
		try {
			System.out.println(task1.get());
			System.out.println(task2.get());
			System.out.println(task3.get().size());
			System.out.printf("%b, %b, %d\n", task1.get(), task2.get(), task3.get().size());
			System.out.println("Done.");
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		} finally {
			executors.shutdown();
		}
	}
	
	public static void main(String[] args) {
		new CountDownLatchNewExample().executeBasedOnThread();
		System.out.println("Finished.");
	}
}
