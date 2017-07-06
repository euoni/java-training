package java8.ch02.ex01;

import java.util.ArrayList;
import java.util.List;

public class LongWordCounter {
	private static int SEGMENT = 5;

	public static int count(List<String> words) throws InterruptedException {
		final List<Counter> threads = new ArrayList<>();
		for (int i = 0; i < words.size(); i += SEGMENT) {
			final Counter counter = new Counter(words.subList(i, Math.min(i + SEGMENT, words.size())));
			threads.add(counter);
			counter.start();
		}

		int count = 0;
		for (final Counter counter : threads) {
			counter.join();
			count += counter.getCount();
		}

		return count;
	}

	private static class Counter extends Thread {
		private final List<String> words;
		private int count;

		public Counter(List<String> words) {
			this.words = words;
			this.count = 0;
		}

		@Override
		public void run() {
			for (final String word : words)
				if (word.length() > 12)
					count++;
		}

		public int getCount() {
			return count;
		}
	}
}
