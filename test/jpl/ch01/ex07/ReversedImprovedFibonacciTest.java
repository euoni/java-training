package jpl.ch01.ex07;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ReversedImprovedFibonacciTest {
	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ReversedImprovedFibonacci.main(null);

		sc.stop();
		sc.assertEquals("9: 1", "8: 1", "7: 2 *", "6: 3", "5: 5", "4: 8 *", "3: 13", "2: 21", "1: 34 *");
	}
}
