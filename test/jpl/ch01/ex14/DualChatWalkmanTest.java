package jpl.ch01.ex14;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch01.ex14.DualChatWalkman.Listener;

public class DualChatWalkmanTest {
	@Test
	public void testChat1() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final DualChatWalkman walkman = new DualChatWalkman();
		walkman.chat(Listener.ONE, "msg1");

		sc.stop();
		sc.assertEquals("1: msg1");
	}

	@Test
	public void testChat2() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final DualChatWalkman walkman = new DualChatWalkman();
		walkman.chat(Listener.TWO, "msg2");

		sc.stop();
		sc.assertEquals("2: msg2");
	}
}
