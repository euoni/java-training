package jpl.ch22.ex05;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class DiceTest {
	private final long seed = 0x5eed;

	@Test
	public void testCount() {
		final Dice dice = new Dice(() -> 0);

		final StdoutCapture sc = new StdoutCapture();
		sc.start();
		dice.print();
		sc.stop();
		sc.assertEquals("sum	observation	ground truth", "2	NaN	NaN", "3	NaN	NaN", "4	NaN	NaN",
				"5	NaN	NaN", "6	NaN	NaN", "7	NaN	NaN", "8	NaN	NaN", "9	NaN	NaN", "10	NaN	NaN",
				"11	NaN	NaN", "12	NaN	NaN", "Χ^2 = NaN");

		dice.count(1);

		sc.start();
		dice.print();
		sc.stop();
		sc.assertEquals("sum	observation	ground truth", "2	1.000000	0.027778", "3	0.000000	0.055556",
				"4	0.000000	0.083333", "5	0.000000	0.111111", "6	0.000000	0.138889",
				"7	0.000000	0.166667", "8	0.000000	0.138889", "9	0.000000	0.111111",
				"10	0.000000	0.083333", "11	0.000000	0.055556", "12	0.000000	0.027778", "Χ^2 = 35.0000");

	}
}
