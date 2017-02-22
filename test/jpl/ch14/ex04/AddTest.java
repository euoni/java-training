package jpl.ch14.ex04;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class AddTest {
	@Test
	public void testAdd() {
		new Add();
	}

	@Test
	public void testMain() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Add.main(null);

		sc.stop();
		sc.assertEquals("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15");
	}
}
