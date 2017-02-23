package jpl.ch14.ex06;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class TimeNotifierTest {
	@Test
	public void testMain() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		TimeNotifier.main(null);

		sc.stop();
		sc.assertEquals("msg7", "msg7", "msg15", "msg7");
	}
}
