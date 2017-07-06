package java8.ch02.ex04;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntArrayStream {
	public static void main(String[] args) {
		final int[] values = { 1, 4, 9, 16 };

		System.out.println("Stream.of(values):");
		Stream.of(values).forEach(System.out::println);

		System.out.println("IntStream.of(values):");
		IntStream.of(values).forEach(System.out::println);
	}
}
