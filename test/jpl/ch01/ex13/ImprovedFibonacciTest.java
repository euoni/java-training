package jpl.ch01.ex13;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ImprovedFibonacciTest {
	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ImprovedFibonacci.main(null);

		sc.stop();
		sc.assertEquals("1: 1", "2: 1", "3: 2 *", "4: 3", "5: 5", "6: 8 *", "7: 13", "8: 21", "9: 34 *");
	}
}
