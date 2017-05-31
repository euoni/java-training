package jpl.ch22.ex05;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.stream.LongStream;

public class Dice {
	private final long[] sum;
	private final IntSupplier generator;

	public Dice(IntSupplier generator) {
		this.sum = new long[11];
		this.generator = generator;
	}

	public void count(long count) {
		while (count-- > 0) {
			final int i = generator.getAsInt();
			final int j = generator.getAsInt();
			sum[i + j]++;
		}
	}

	public void print() {
		final long total = LongStream.of(sum).sum();
		System.out.println("sum\tobservation\tground truth");
		double xi2 = 0;
		for (int i = 0; i < sum.length; i++) {
			final long o = sum[i];
			final double e = (i < 6 ? i + 1 : 11 - i) * total / 36.;
			xi2 += (o - e) * (o - e) / e;

			final double op = (double) o / total;
			final double ep = e / total;
			System.out.printf("%d\t%.6f\t%.6f%n", i + 2, op, ep);
		}
		System.out.printf("Χ^2 = %.4f%n", xi2);
	}

	public static void main(String[] args) {
		final long seed = 0x5eed;
		final long count = 1000000;

		final HashMap<String, Function<Random, IntSupplier>> methodMap = new HashMap<>();
		methodMap.put("abs(nextInt()) % 6", r -> () -> Math.abs(r.nextInt()) % 6);
		methodMap.put("nextInt(6)", r -> () -> r.nextInt(6));
		methodMap.put("abs(nextLong()) % 6", r -> () -> (int) (Math.abs(r.nextLong()) % 6));
		methodMap.put("nextFloat() * 6", r -> () -> (int) (r.nextFloat() * 6));
		methodMap.put("nextDouble() * 6", r -> () -> (int) (r.nextDouble() * 6));

		for (final Entry<String, Function<Random, IntSupplier>> e : methodMap.entrySet()) {
			System.out.println("### " + e.getKey() + " ###");
			final Random random = new Random(seed);
			final Dice dice = new Dice(e.getValue().apply(random));
			dice.count(count);
			dice.print();
			System.out.println();
		}
	}
}
