package jpl.ch09.ex04;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ExerciseTest {
	@Test
	public void testExercise() {
		new Exercise();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Exercise.main(null);

		sc.stop();
		sc.assertEquals(new String[] { "3 << 2L - 1 = (int)6 [actual (int)6]",
				"(3 << 2L) - 1 = (long)11 [actual (int)11]", "10 < 12 == 6 > 17 = (bool)false [actual (boolean)false]",
				"10 << 12 == 6 >> 17 = (bool)false [actual (boolean)false]",
				"13.5e-1 % Float.POSITIVE_INFINITY = (double)1.35 [actual (double)1.35]",
				"Float.POSITIVE_INFINITY + double.NEGATIVE_INFINITY = (double)NaN [actual (double)NaN]",
				"double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY = (double)Infinity [actual (double)Infinity]",
				"0.0 / -0.0 == -0.0 / 0.0 = (boolean)false [actual (boolean)false]",
				"int.MAX_VALUE + int.MIN_VALUE = (int)-1 [actual (int)-1]",
				"Long.MAX_VALUE + 5 = (long)-9223372036854775804 [actual (Long)-9223372036854775804]",
				"(short) 5 * (byte) 10 = (int)50 [actual (int)50]", });

	}
}
