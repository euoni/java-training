package jpl.ch01.ex09;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FibonacciArrayTest {
	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		FibonacciArray.main(null);

		sc.stop();
		sc.assertEquals("1", "1", "2", "3", "5", "8", "13", "21", "34");
	}
}
