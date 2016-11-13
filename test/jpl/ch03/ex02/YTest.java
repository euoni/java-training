package jpl.ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class YTest {
	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Y.main(null);

		sc.stop();
		sc.assertEquals("X Init	xMask=00ff	fullMask=0000", "X Const	xMask=00ff	fullMask=00ff",
				"Y Init	xMask=00ff	yMask=ff00	fullMask=00ff", "Y Const	xMask=00ff	yMask=ff00	fullMask=ffff");
	}

	@Test
	public void testMask() {
		final Y y = new Y();
		assertThat(y.mask(0x1111), is(0x1111));
	}
}
