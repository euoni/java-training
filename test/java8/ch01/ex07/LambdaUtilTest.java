package java8.ch01.ex07;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class LambdaUtilTest {
	@Test
	public void testLambdaUtil() {
		new LambdaUtil();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		LambdaUtil.main(null);

		sc.stop();
		sc.assertEquals("one", "two");
	}
}
