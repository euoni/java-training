package java8.ch02.ex09;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class StreamOfListTest {
	@RunWith(Parameterized.class)
	public static class FlattenTest {
		@Parameters(name = "flatten{index}")
		public static Collection<Function<Stream<ArrayList<Integer>>, ArrayList<Integer>>> data() {
			return Arrays.asList(StreamOfList::<Integer>flatten0, StreamOfList::<Integer>flatten1,
					StreamOfList::<Integer>flatten2);
		}

		@Parameter(0)
		public Function<Stream<ArrayList<Integer>>, ArrayList<Integer>> f;

		@Test
		public void testEmpty() {
			assertThat(f.apply(Stream.of()), empty());
		}

		@Test
		public void testOne() {
			final ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(0, 1, 2));

			assertThat(f.apply(Stream.of(arr1)), contains(0, 1, 2));
		}

		@Test
		public void testOneParallel() {
			final ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(0, 1, 2));

			assertThat(f.apply(Stream.of(arr1).parallel()), contains(0, 1, 2));
		}

		@Test
		public void testTwo() {
			final ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(0, 1, 2));
			final ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(3, 4, 5));

			assertThat(f.apply(Stream.of(arr1, arr2)), contains(0, 1, 2, 3, 4, 5));
		}

		@Test
		public void testRepeat() {
			final ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 1, 2));

			assertThat(f.apply(Stream.of(arr, arr, arr)), contains(0, 1, 2, 0, 1, 2, 0, 1, 2));
		}

		@Test
		public void testParallel() {
			final ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(0, 1, 2));
			final ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(0, 1, 2));
			final ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(0, 1, 2));

			assertThat(f.apply(Stream.of(arr1, arr2, arr3).parallel()), contains(0, 1, 2, 0, 1, 2, 0, 1, 2));
		}

		@Test
		public void testParallelRepeat() {
			final ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(0, 1, 2));

			assertThat(f.apply(Stream.of(arr, arr, arr).parallel()), contains(0, 1, 2, 0, 1, 2, 0, 1, 2));
		}
	}

	public static class CtorTest {
		@Test
		public void test() {
			new StreamOfList();
		}
	}
}
