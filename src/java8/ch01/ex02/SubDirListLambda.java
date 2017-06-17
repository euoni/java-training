package java8.ch01.ex02;

import java.io.File;
import java.util.Arrays;

public class SubDirListLambda {
	public static void main(String[] args) {
		final File file = new File(args[0]);
		final File[] dirs = file.listFiles(f -> f.isDirectory());
		Arrays.stream(dirs).forEach(System.out::println);
	}
}
