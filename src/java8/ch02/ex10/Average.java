package java8.ch02.ex10;

import java.util.OptionalDouble;
import java.util.stream.Stream;

public class Average {
	public static double calc(Stream<Double> stream) {
		// reduceとcountは終端処理なので、どちらかしか呼べない
		return stream.reduce(new Counter(), Counter::add, Counter::merge).average().getAsDouble();
	}

	private static class Counter {
		private final double sum;
		private final double count;

		public Counter() {
			this(0, 0);
		}

		public Counter(double sum, double count) {
			this.sum = sum;
			this.count = count;
		}

		public Counter add(double x) {
			return new Counter(sum + x, count + 1);
		}

		public Counter merge(Counter c) {
			return new Counter(sum + c.sum, count + c.count);
		}

		public OptionalDouble average() {
			return count == 0 ? OptionalDouble.empty() : OptionalDouble.of(sum / count);
		}
	}
}
