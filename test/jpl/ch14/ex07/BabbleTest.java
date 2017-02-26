package jpl.ch14.ex07;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class BabbleTest {
	@Test
	public void testMainNotYield() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Babble.main(new String[] { "false", "2", "msg" });
		Thread.sleep(100);

		sc.stop();
		sc.assertEquals("msg", "msg");
	}

	@Test
	public void testMainYield() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Babble.main(new String[] { "true", "2", "msg" });
		Thread.sleep(100);

		sc.stop();
		sc.assertEquals("msg", "msg");
	}
}
