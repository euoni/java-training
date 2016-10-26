package jpl.ch01.ex11;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class StringDemoTest {
	@Test
	public void testStringDemo() {
		new StringDemo();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		StringDemo.main(null);

		sc.stop();
		sc.assertEquals("Name = Ken Arnold");
	}
}
