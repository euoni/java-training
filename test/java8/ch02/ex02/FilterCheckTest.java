package java8.ch02.ex02;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FilterCheckTest {
	@Test
	public void testCtor() {
		new FilterCheck();
	}

	@Test
	public void testMain0() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		FilterCheck.main("1", "12", "123");

		sc.stop();
		sc.assertEquals("filter: 1", "filter: 12", "filter: 123");
	}

	@Test
	public void testMain1() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		FilterCheck.main("1", "12", "123", "1234");

		sc.stop();
		sc.assertEquals("filter: 1", "filter: 12", "filter: 123", "filter: 1234", "1234");
	}

	@Test
	public void testMain5() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		FilterCheck.main("1", "12", "123", "1234", "12345", "123456", "1234567", "12345678");

		sc.stop();
		sc.assertEquals("filter: 1", "filter: 12", "filter: 123", "filter: 1234", "1234", "filter: 12345", "12345",
				"filter: 123456", "123456", "filter: 1234567", "1234567", "filter: 12345678", "12345678");
	}

	@Test
	public void testMain6() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		FilterCheck.main("1", "12", "123", "1234", "12345", "123456", "1234567", "12345678", "123456789");

		sc.stop();
		sc.assertEquals("filter: 1", "filter: 12", "filter: 123", "filter: 1234", "1234", "filter: 12345", "12345",
				"filter: 123456", "123456", "filter: 1234567", "1234567", "filter: 12345678", "12345678");
	}
}
