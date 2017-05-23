package jpl.ch22.ex06;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GaussianDistribution {
	private static final int COUNT = 1000;
	private static final double FACTOR = 10;
	private static final double MIN = -3;
	private static final double MAX = 3;
	private static final int COL = 50;

	public static void main(String[] args) {
		final Random r = new Random(0x5eed);

		final Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < COUNT; i++) {
			final int v = (int) (r.nextGaussian() * FACTOR);
			map.put(v, map.getOrDefault(v, 0) + 1);
		}

		final Integer maxNum = map.values().stream().reduce(Integer::max).get();

		for (double i = MIN; i < MAX; i += 1. / FACTOR) {
			final Integer num = map.getOrDefault((int) (i * FACTOR), 0);
			final double p = num * 1. / COUNT;
			System.out.printf("% .1f: %.3f ", i, p);

			for (int j = 0; j < num * COL / maxNum; j++) {
				System.out.print('*');
			}
			System.out.println();
		}
	}
}
