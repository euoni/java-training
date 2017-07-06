package java8.ch02.ex05;

import java.util.stream.Stream;

public class RandStream {
	public static Stream<Long> stream(long seed, long a, long c, long m) {
		return Stream.iterate(seed, x -> (a * x + c) % m);
	}
}
