package jpl.ch01.ex14;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class WalkmanTest {
	@Test
	public void testPlay() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final String music = "music";
		final Walkman walkman = new Walkman();
		walkman.setTape(music);
		walkman.play();

		sc.stop();
		sc.assertEquals(music);
	}
}
