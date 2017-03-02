package jpl.ch16.ex11;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class GameTest {
	@Test
	public void testMainDefaultHeadPlayer() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Game.main(new String[] { "jpl.ch16.ex11.HeadPlayer" });

		sc.stop();
		sc.assertEquals("jpl.ch16.ex11.HeadPlayer's score: -1");
	}

	@Test
	public void testMainDefaultTailPlayer() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Game.main(new String[] { "jpl.ch16.ex11.TailPlayer" });

		sc.stop();
		sc.assertEquals("jpl.ch16.ex11.TailPlayer's score: 1");
	}

	@Test
	public void testMainCustomHeadPlayer() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Game.main(new String[] { "HeadPlayer" });

		sc.stop();
		sc.assertEquals("HeadPlayer's score: -1");
	}

	@Test
	public void testMainCustomTailPlayer() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Game.main(new String[] { "TailPlayer" });

		sc.stop();
		sc.assertEquals("TailPlayer's score: 1");
	}
}
