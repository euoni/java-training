package jpl.ch03.ex05;

public abstract class Benchmark {
	abstract void benchmark();

	public final long repeat(int count) {
		final long start = System.nanoTime();
		for (int i = 0; i < count; i++)
			benchmark();
		return (System.nanoTime() - start);
	}
}
