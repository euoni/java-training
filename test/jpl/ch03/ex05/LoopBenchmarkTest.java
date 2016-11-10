package jpl.ch03.ex05;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class LoopBenchmarkTest {
	@Test
	public void testLoopBenchmark() {
		final LoopBenchmark benchmark = new LoopBenchmark(1000);
		assertThat(benchmark.getLoopCount(), is(1000));
	}

	@Test
	public void testRepeat() {
		final LoopBenchmark benchmark = new LoopBenchmark(1000);
		assertThat(benchmark.repeat(1), greaterThan(0L));
	}
}
