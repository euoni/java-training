package java8.ch01.ex06;

import static java8.ch01.ex06.RunnableEx.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class RunnableExTest {
	@Test
	public void test() throws InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final Thread t = new Thread(uncheck(() -> {
			System.out.println("Zzz");
		}));
		t.start();
		t.join();

		sc.stop();
		sc.assertEquals("Zzz");
	}

	@Test(expected = RuntimeException.class)
	public void testEx() {
		uncheck(() -> {
			throw new IllegalStateException();
		}).run();
	}
}
