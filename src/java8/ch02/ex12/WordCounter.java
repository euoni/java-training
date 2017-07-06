package java8.ch02.ex12;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class WordCounter {
	private final static int LEN = 6;

	public static void main(String... args) {
		final AtomicInteger[] shortWords = new AtomicInteger[LEN];
		Arrays.setAll(shortWords, x -> new AtomicInteger(0));

		Arrays.stream(args).parallel().forEach(s -> {
			if (s.length() < LEN)
				shortWords[s.length()].getAndIncrement();
		});

		System.out.println(Arrays.toString(shortWords));
	}
}
