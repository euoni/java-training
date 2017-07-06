package java8.ch02.ex02;

import java.util.Arrays;

public class FilterCheck {
	public static void main(String... args) {
		Arrays.stream(args).filter(w -> {
			System.out.println("filter: " + w);
			return w.length() > 3;
		}).limit(5).forEach(System.out::println);
	}
}
