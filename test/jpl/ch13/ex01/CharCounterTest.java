package jpl.ch13.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CharCounterTest {
	@Test
	public void testCharCounter() {
		new CharCounter();
	}

	@Test
	public void testCount() {
		final String str = "abcdefgh abcd ab a";
		assertThat(CharCounter.count(str, 'a'), is(4));
		assertThat(CharCounter.count(str, 'b'), is(3));
		assertThat(CharCounter.count(str, 'c'), is(2));
		assertThat(CharCounter.count(str, 'd'), is(2));
		assertThat(CharCounter.count(str, 'e'), is(1));
		assertThat(CharCounter.count(str, 'f'), is(1));
		assertThat(CharCounter.count(str, 'g'), is(1));
		assertThat(CharCounter.count(str, 'h'), is(1));
		assertThat(CharCounter.count(str, 'i'), is(0));
		assertThat(CharCounter.count(str, ' '), is(3));
	}
}
