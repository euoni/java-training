package java8.ch05.ex04;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class CalTest {
	@Test
	public void testMainError() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Cal.main(new String[] {});

		sc.stop();
		sc.assertEquals("Usage: java Cal 3 2013");
	}

	@Test
	public void testMainNumError() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Cal.main(new String[] { "a", "b" });

		sc.stop();
		sc.assertEquals("Invalid argument");
	}

	@Test
	public void testMain201303() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Cal.main(new String[] { "3", "2013" });

		sc.stop();
		sc.assertEquals("             1  2  3 ", " 4  5  6  7  8  9 10 ", "11 12 13 14 15 16 17 ",
				"18 19 20 21 22 23 24 ", "25 26 27 28 29 30 31 ");
	}

	@Test
	public void testMain201304() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Cal.main(new String[] { "4", "2013" });

		sc.stop();
		sc.assertEquals(" 1  2  3  4  5  6  7 ", " 8  9 10 11 12 13 14 ", "15 16 17 18 19 20 21 ",
				"22 23 24 25 26 27 28 ", "29 30 ");
	}
}
