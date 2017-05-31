package jpl.ch22.ex01;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class DisplayFloatArrayTest {
	@Test
	public void testDisplayFloatArray() {
		new DisplayFloatArray();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		DisplayFloatArray.main(null);

		sc.stop();
		sc.assertEquals(" 0.0000  1.2346  2.4691  3.7037  4.9383  6.1728  7.4074  8.6420  9.8765 11.1111 ",
				"12.3457 13.5802 14.8148 16.0494 17.2840 18.5185 19.7531 20.9877 22.2222 23.4568 ");
	}
}
