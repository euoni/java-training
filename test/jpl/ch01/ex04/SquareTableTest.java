package jpl.ch01.ex04;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class SquareTableTest {
	@Test
	public void SquareTable() {
		new SquareTable();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		SquareTable.main(null);

		sc.stop();
		sc.assertEquals("11 * 11 = 121", "12 * 12 = 144", "13 * 13 = 169", "14 * 14 = 196", "15 * 15 = 225",
				"16 * 16 = 256", "17 * 17 = 289", "18 * 18 = 324", "19 * 19 = 361", "20 * 20 = 400");
	}
}
