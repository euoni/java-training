package jpl.ch14.ex02;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class PrintServerTest {
	@Test
	public void testPrint() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final PrintServer server = new PrintServer();
		server.print(new PrintJob("paper1"));

		try {
			// wait for printing
			Thread.sleep(100);
		} catch (final InterruptedException e) {
			;
		}

		sc.stop();
		sc.assertEquals("paper1");
	}

	@Test
	public void testRun() {
		final PrintServer server = new PrintServer();
		server.run();
	}
}
