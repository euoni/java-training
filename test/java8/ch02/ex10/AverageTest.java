package java8.ch02.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class AverageTest {
	@RunWith(Parameterized.class)
	public static class CalcTest {
		@Parameters(name = "{1}")
		public static Collection<Object[]> data() {
			final double[][] testData = new double[][] { { 0. }, { 0., 1. }, { 1., 1., 1., 1. }, { 1., 0.5, 0.25 } };
			return Stream.of(testData).map(d -> new Object[] { d, Arrays.toString(d).replaceAll("^.|.$", "") })
					.collect(Collectors.toList());
		}

		@Parameter(0)
		public double[] data;

		@Parameter(1)
		public String label;

		@Test
		public void testSerial() {
			final double avg = DoubleStream.of(data).average().getAsDouble();
			assertThat(Average.calc(DoubleStream.of(data).boxed()), is(avg));
		}

		@Test
		public void testParallel() {
			final double avg = DoubleStream.of(data).average().getAsDouble();
			assertThat(Average.calc(DoubleStream.of(data).boxed().parallel()), is(avg));
		}
	}

	public static class EmptyTest {
		@Test(expected = NoSuchElementException.class)
		public void test() {
			Average.calc(Stream.of());
		}
	}

	public static class CtorTest {
		@Test
		public void test() {
			new Average();
		}
	}
}
