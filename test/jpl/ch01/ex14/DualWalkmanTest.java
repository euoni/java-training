package jpl.ch01.ex14;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class DualWalkmanTest {
	@Test
	public void testPlay() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final String music = "music";
		final DualChatWalkman walkman = new DualChatWalkman();
		walkman.setTape(music);
		walkman.play();

		sc.stop();
		sc.assertEquals("1: " + music, "2: " + music);
	}
}
