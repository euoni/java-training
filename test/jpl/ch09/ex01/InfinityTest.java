package jpl.ch09.ex01;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class InfinityTest {
	@Test
	public void testInfinity() {
		new Infinity();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Infinity.main(null);

		sc.stop();
		sc.assertEquals(new String[] { "(+∞) + (+∞) = Infinity", "(+∞) + (-∞) = NaN", "(+∞) - (+∞) = NaN",
				"(+∞) - (-∞) = Infinity", "(+∞) * (+∞) = Infinity", "(+∞) * (-∞) = -Infinity", "(+∞) / (+∞) = NaN",
				"(+∞) / (-∞) = NaN", });
	}
}
