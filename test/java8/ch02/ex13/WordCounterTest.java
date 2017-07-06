package java8.ch02.ex13;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class WordCounterTest {
	@Test
	public void testCtor() {
		new WordCounter();
	}

	@Test
	public void testEmpty() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		WordCounter.main();

		sc.stop();
		sc.assertEquals("[0, 0, 0, 0, 0, 0]");
	}

	@Test
	public void testZeroLength() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		WordCounter.main("");

		sc.stop();
		sc.assertEquals("[1, 0, 0, 0, 0, 0]");
	}

	@Test
	public void testOneLong() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		WordCounter.main("longlonglong");

		sc.stop();
		sc.assertEquals("[0, 0, 0, 0, 0, 0]");
	}

	@Test
	public void testThreeShort() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		WordCounter.main("a", "short", "word");

		sc.stop();
		sc.assertEquals("[0, 1, 0, 0, 1, 1]");
	}

	@Test
	public void testShortAndLong() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		WordCounter.main("short", "word", "and", "longlonglong");

		sc.stop();
		sc.assertEquals("[0, 0, 0, 1, 1, 1]");
	}
}
