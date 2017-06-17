package java8.ch01.ex01;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class SortTest {
	@Test
	public void testSort() {
		new Sort();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Sort.main(null);

		sc.stop();
		sc.assertEquals("Thread used in Arrays.sort: ", Thread.currentThread().getName());
	}
}
