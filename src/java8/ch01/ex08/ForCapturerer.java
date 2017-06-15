package java8.ch01.ex08;

import java.util.ArrayList;
import java.util.List;

public class ForCapturerer {
	public static void main(String[] args) {
		final String[] names = { "Peter", "Paul", "Mary" };

		{
			final List<Runnable> runners = new ArrayList<>();
			for (final String name : names)
				runners.add(() -> System.out.println(name));
			runners.forEach(Runnable::run);
		}

		{
			final List<Runnable> runners = new ArrayList<>();
			for (int i = 0; i < names.length; i++) {
				final String name = names[i];
				// iはfinalでないのでキャプチャできない
				runners.add(() -> System.out.println(name));
			}
			runners.forEach(Runnable::run);
		}
	}
}
