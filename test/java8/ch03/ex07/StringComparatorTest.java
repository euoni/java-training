package java8.ch03.ex07;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class StringComparatorTest {
	@RunWith(Parameterized.class)
	public static class GetTest {
		@Parameters(name = "{0}, {1}, {2}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] { { false, false, false }, { true, false, false },
					{ false, true, false }, { true, true, false }, { false, false, true }, { true, false, true },
					{ false, true, true }, { true, true, true } });
		}

		@Parameter(0)
		public boolean reverse;

		@Parameter(1)
		public boolean ignoreCase;

		@Parameter(2)
		public boolean ignoreSpace;

		@Test
		public void test() {
			final Comparator<String> comparator = StringComparator.get(reverse, ignoreCase, ignoreSpace);

			// Order
			assertThat(comparator.compare("a", "b"), reverse ? greaterThan(0) : lessThan(0));
			assertThat(comparator.compare("a", "a"), is(0));
			assertThat(comparator.compare("b", "a"), reverse ? lessThan(0) : greaterThan(0));
			assertThat(comparator.compare("a", "aa"), reverse ? greaterThan(0) : lessThan(0));
			assertThat(comparator.compare("aa", "a"), reverse ? lessThan(0) : greaterThan(0));

			// Case
			assertThat(comparator.compare("A", "a"), ignoreCase ? is(0) : (reverse ? greaterThan(0) : lessThan(0)));
			assertThat(comparator.compare("a", "A"), ignoreCase ? is(0) : (reverse ? lessThan(0) : greaterThan(0)));

			// Space
			assertThat(comparator.compare("", " "), ignoreSpace ? is(0) : (reverse ? greaterThan(0) : lessThan(0)));
			assertThat(comparator.compare(" ", ""), ignoreSpace ? is(0) : (reverse ? lessThan(0) : greaterThan(0)));
			assertThat(comparator.compare("a b", "ab"), ignoreSpace ? is(0) : (reverse ? greaterThan(0) : lessThan(0)));
			assertThat(comparator.compare("ab", "a b"), ignoreSpace ? is(0) : (reverse ? lessThan(0) : greaterThan(0)));
		}
	}

	public static class CtorTest {
		@Test
		public void test() {
			new StringComparator();
		}
	}
}
