package jpl.ch01.ex03;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FibonacciTest {
	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Fibonacci.main(null);

		sc.stop();
		sc.assertEquals("Fibonacci", "1", "1", "2", "3", "5", "8", "13", "21", "34");
	}
}
