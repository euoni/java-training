package jpl.ch13.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CharCounterTest {
	@Test
	public void testStringCounter() {
		new StringCounter();
	}

	@Test
	public void testCount() {
		final String str1 = "abcdefgh abcd ab a";
		assertThat(StringCounter.count(str1, "a"), is(4));
		assertThat(StringCounter.count(str1, "ab"), is(3));
		assertThat(StringCounter.count(str1, "abc"), is(2));
		assertThat(StringCounter.count(str1, "abcd"), is(2));
		assertThat(StringCounter.count(str1, "abcde"), is(1));
		assertThat(StringCounter.count(str1, "abcdef"), is(1));
		assertThat(StringCounter.count(str1, "abcdefg"), is(1));
		assertThat(StringCounter.count(str1, "abcdefgh"), is(1));
		assertThat(StringCounter.count(str1, "abcdefghi"), is(0));

		final String str2 = "ababababa";
		assertThat(StringCounter.count(str2, "ab"), is(4));
	}
}
