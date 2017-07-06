package java8.ch02.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class RandStreamTest {
	@Test
	public void testCtor() {
		new RandStream();
	}

	@Test
	public void testStream() {
		final long seed = 0L;
		final long a = 25214903917L;
		final long c = 11;
		final long m = 1L << 48;

		final List<Long> list = RandStream.stream(seed, a, c, m).limit(5).collect(Collectors.toList());

		assertThat(list.get(0), is(seed));
		assertThat(list.get(1), is((a * seed + c) % m));
		assertThat(list.get(2), is((a * ((a * seed + c) % m) + c) % m));
		assertThat(list.get(3), is((a * ((a * ((a * seed + c) % m) + c) % m) + c) % m));
		assertThat(list.get(4), is((a * ((a * ((a * ((a * seed + c) % m) + c) % m) + c) % m) + c) % m));
	}
}
