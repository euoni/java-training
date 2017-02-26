package jpl.ch14.ex08;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class SafeFriendlyTest {
	@Test
	public void testMain() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		SafeFriendly.main(null);
		Thread.sleep(100);

		sc.stop();
		sc.assertEquals("Thread1 in jareth.hug() trying to invoke cory.hugBack()", "Thread1 in cory.hugBack()",
				"Thread2 in cory.hug() trying to invoke jareth.hugBack()", "Thread2 in jareth.hugBack()");
	}
}
