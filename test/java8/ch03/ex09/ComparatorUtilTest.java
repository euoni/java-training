package java8.ch03.ex09;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class ComparatorUtilTest {
	@Test
	public void testCtor() {
		new ComparatorUtil();
	}

	@Test
	public void testLexicographicComparatorOneField() {
		final Comparator<Object> comparator = ComparatorUtil.lexicographicComparator("f1");

		assertThat(comparator.compare(new Data(0), new Data(1)), lessThan(0));
		assertThat(comparator.compare(new Data(1), new Data(0)), greaterThan(0));
		assertThat(comparator.compare(new Data(0), new Data(0)), is(0));
	}

	@Test
	public void testLexicographicComparatorTwoFields() {
		final Comparator<Object> comparator = ComparatorUtil.lexicographicComparator("f1", "f2");

		assertThat(comparator.compare(new Data(0, 0), new Data(0, 1)), lessThan(0));
		assertThat(comparator.compare(new Data(0, 1), new Data(0, 0)), greaterThan(0));
		assertThat(comparator.compare(new Data(0, 0), new Data(1, 0)), lessThan(0));
		assertThat(comparator.compare(new Data(1, 0), new Data(0, 0)), greaterThan(0));
		assertThat(comparator.compare(new Data(0, 1), new Data(1, 0)), lessThan(0));
		assertThat(comparator.compare(new Data(1, 0), new Data(0, 1)), greaterThan(0));
		assertThat(comparator.compare(new Data(0, 0), new Data(0, 0)), is(0));
	}

	@Test
	public void testLexicographicComparatorTwoFieldsReversed() {
		{
			final Comparator<Object> comparator = ComparatorUtil.lexicographicComparator("f1", "f2");
			assertThat(comparator.compare(new Data(1, 0), new Data(0, 1)), greaterThan(0));
		}
		{
			final Comparator<Object> comparator = ComparatorUtil.lexicographicComparator("f2", "f1");
			assertThat(comparator.compare(new Data(1, 0), new Data(0, 1)), lessThan(0));
		}
	}

	@Test(expected = ClassCastException.class)
	public void testLexicographicComparatorDifferentTypes() {
		final Comparator<Object> comparator = ComparatorUtil.lexicographicComparator("f1");

		comparator.compare(new Data(0), new Data("a"));
	}

	@Test(expected = RuntimeException.class)
	public void testLexicographicComparatorNoFiled() {
		final Comparator<Object> comparator = ComparatorUtil.lexicographicComparator("f3");

		comparator.compare(new Data(0), new Data(0));
	}

	private static class Data {
		@SuppressWarnings("unused")
		public Object f1, f2;

		public Data(Object f1) {
			this(f1, null);
		}

		public Data(Object f1, Object f2) {
			this.f1 = f1;
			this.f2 = f2;
		}
	}
}
