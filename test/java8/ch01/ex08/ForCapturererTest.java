package java8.ch01.ex08;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ForCapturererTest {
	@Test
	public void testLambdaUtil() {
		new ForCapturerer();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ForCapturerer.main(null);

		sc.stop();
		sc.assertEquals("Peter", "Paul", "Mary", "Peter", "Paul", "Mary");
	}
}
