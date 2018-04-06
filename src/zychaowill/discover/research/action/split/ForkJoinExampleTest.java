package zychaowill.discover.research.action.split;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import zychaowill.utils.NumberFactory;

public class ForkJoinExampleTest {

	public static void main(String[] args) {
		Integer[] numbers = NumberFactory.integers(10);
		System.out.println(Arrays.stream(numbers).map(String::valueOf).collect(Collectors.joining(",")));
		ForkJoinPool pool = new ForkJoinPool();
		pool.submit(new ForkJoinExample<Integer>(numbers, (x, y) -> x.compareTo(y)));
		pool.shutdown();
		System.out.println(Arrays.stream(numbers).map(String::valueOf).collect(Collectors.joining(",")));
	}
}
