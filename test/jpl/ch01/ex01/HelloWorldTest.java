package jpl.ch01.ex01;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class HelloWorldTest {
	@Test
	public void testHelloWorld() {
		new HelloWorld();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		HelloWorld.main(null);

		sc.stop();
		sc.assertEquals("Hello, world");
	}
}
