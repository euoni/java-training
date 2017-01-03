package jpl.ch09.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class BitCountTest {
	private void count(Function<Integer, Integer> algo) {
		int i;

		i = 0x00000000;
		assertThat(algo.apply(i), is(0));

		i = 0xAAAAAAAA;
		assertThat(algo.apply(i), is(16));

		i = 0xCCCC5555;
		assertThat(algo.apply(i), is(16));

		i = 0xFFFFFFFF;
		assertThat(algo.apply(i), is(32));
	}

	@Test
	public void testBitCount() {
		new BitCount();
		return;
	}

	@Test
	public void testSift() {
		count(BitCount::sift);
	}

	@Test
	public void testAlgo1() {
		count(BitCount::algo1);
	}

	@Test
	public void testAlgo2() {
		count(BitCount::algo2);
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		BitCount.main(null);

		sc.stop();
		sc.assertEquals(
				new String[] { "10101010101010101010101010101010: 16", "11001100110011000101010101010101: 16" });

	}
}
