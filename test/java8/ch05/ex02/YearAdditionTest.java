package java8.ch05.ex02;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class YearAdditionTest {
	@Test
	public void testCtor() {
		new YearAddition();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		YearAddition.main(null);

		sc.stop();
		sc.assertEquals("2000-02-29 + 1 year = 2001-02-28", "2000-02-29 + 4 years = 2004-02-29",
				"2000-02-29 + 1 year + 1 year + 1 year + 1 year = 2004-02-28");
	}
}
