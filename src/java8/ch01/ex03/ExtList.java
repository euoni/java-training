package java8.ch01.ex03;

import java.io.File;
import java.util.Arrays;

public class ExtList {
	public static void main(String[] args) {
		final File file = new File(args[0]);
		// args is captured
		final String[] files = file.list((f, s) -> s.endsWith(args[1]));
		Arrays.stream(files).forEach(System.out::println);
	}
}
