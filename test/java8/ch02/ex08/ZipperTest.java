package java8.ch02.ex08;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class ZipperTest {
	@Test
	public void testCtor() {
		new Zipper();
	}

	@Test
	public void testZipEmpty() {
		final List<?> list = Zipper.zip(Stream.of(), Stream.of()).collect(Collectors.toList());

		assertThat(list, empty());
	}

	@Test
	public void testZipSameSize() {
		final List<Integer> list = Zipper.zip(Stream.of(0, 1, 2), Stream.of(10, 11, 12)).collect(Collectors.toList());

		assertThat(list, contains(0, 10, 1, 11, 2, 12));
	}

	@Test
	public void testZipLongShort() {
		final List<Integer> list = Zipper.zip(Stream.of(0, 1, 2), Stream.of(10)).collect(Collectors.toList());

		assertThat(list, contains(0, 10));
	}

	@Test
	public void testZipShortLong() {
		final List<Integer> list = Zipper.zip(Stream.of(0), Stream.of(10, 11, 12)).collect(Collectors.toList());

		assertThat(list, contains(0, 10));
	}
}
