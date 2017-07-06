package java8.ch02.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.stream.Stream;

import org.junit.Test;

public class StreamCounterTest {
	@Test
	public void testCtor() {
		new StreamCounter();
	}

	@Test
	public void testFinite() {
		assertThat(StreamCounter.isFinite(Stream.of(1, 2, 3)), is(true));
	}

	// @Test
	// public void testInfinite() {
	// assertThat(StreamCounter.isFinite(Stream.generate(() -> 0)), is(false));
	// }
}
