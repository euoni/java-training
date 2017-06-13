package java8.ch01.ex01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sort {
	public static void main(String[] args) {
		final Set<Thread> threads = new HashSet<>();

		final Integer[] array = { 5, 4, 3, 2, 1 };
		Arrays.sort(array, (x, y) -> {
			threads.add(Thread.currentThread());
			return Integer.compare(x, y);
		});

		System.out.println("Thread used in Arrays.sort: ");
		threads.forEach(t -> System.out.println(t.getName()));
	}
}
