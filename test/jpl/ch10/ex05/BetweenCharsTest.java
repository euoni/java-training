package jpl.ch10.ex05;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class BetweenCharsTest {
	@Test
	public void testBetweenChars() {
		new BetweenChars();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		BetweenChars.main(null);

		sc.stop();
		sc.assertEquals("abcdefghijklmnopqrstuvwxyz");
	}
}
