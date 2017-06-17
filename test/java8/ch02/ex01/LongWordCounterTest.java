package java8.ch02.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class LongWordCounterTest {
	@Test
	public void testCtor() {
		new LongWordCounter();
	}

	@Test
	public void testCountEmpty() throws InterruptedException {
		assertThat(LongWordCounter.count(Collections.emptyList()), is(0));
	}

	@Test
	public void testCountOneThread() throws InterruptedException {
		assertThat(LongWordCounter.count(Arrays.asList("ABCDEFGHIJKL", "ABCDEFGHIJKLM", "ABCDEFGHIJKLMN")), is(2));
	}

	@Test
	public void testCountTwoThread() throws InterruptedException {
		assertThat(LongWordCounter.count(Arrays.asList("ABCDEFGHIJKL", "ABCDEFGHIJKLM", "ABCDEFGHIJKLMN",
				"ABCDEFGHIJKL", "ABCDEFGHIJKLM", "ABCDEFGHIJKLMN")), is(4));
	}
}
