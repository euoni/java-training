package java8.ch02.ex13;

import java.util.Arrays;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class WordCounter {
	private final static int LEN = 6;

	public static void main(String... args) {
		final int[] shortWords = Arrays.stream(args).parallel().filter(s -> s.length() < LEN)
				.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.counting())).entrySet().stream()
				.collect(() -> new int[LEN], (r, e) -> r[e.getKey()] = e.getValue().intValue(), (r1, r2) -> {
				});

		System.out.println(Arrays.toString(shortWords));
	}
}
