package jpl.ch16.ex12;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class GameTest {
	@Test
	public void testMainDefaultCustomizablePlayer() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Game.main(new String[] { "jpl.ch16.ex12.CustomizablePlayer" });

		sc.stop();
		sc.assertEquals("jpl.ch16.ex12.CustomizablePlayer's score: -1");
	}

	@Test
	public void testMainCustomCustomizablePlayer() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Game.main(new String[] { "CustomizablePlayer" });

		sc.stop();
		sc.assertEquals("CustomizablePlayer's score: 1");
	}
}
