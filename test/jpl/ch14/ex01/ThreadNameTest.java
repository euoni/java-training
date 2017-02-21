package jpl.ch14.ex01;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ThreadNameTest {
	@Test
	public void testThreadName() {
		new ThreadName();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ThreadName.main(null);

		sc.stop();
		sc.assertEquals("main");
	}
}
