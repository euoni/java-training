package jpl.ch14.ex08;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FriendlyTest {
	@Test
	public void testHug() {
		final Friendly f1 = new Friendly("f1");
		final Friendly f2 = new Friendly("f2");

		f1.becomeFrined(f2);

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		f1.hug();

		sc.stop();
		final String name = Thread.currentThread().getName();
		sc.assertEquals(name + " in f1.hug() trying to invoke f2.hugBack()", name + " in f2.hugBack()");
	}
}
