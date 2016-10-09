package jpl.ch01.ex14;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jpl.ch01.ex14.DualChatWalkman.Listener;

public class DualChatWalkmanTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testChat1() {
		final DualChatWalkman walkman = new DualChatWalkman();
		walkman.chat(Listener.ONE, "msg1");

		assertEquals("1: msg1" + System.lineSeparator(), outContent.toString());
	}

	@Test
	public void testChat2() {
		final DualChatWalkman walkman = new DualChatWalkman();
		walkman.chat(Listener.TWO, "msg2");

		assertEquals("2: msg2" + System.lineSeparator(), outContent.toString());
	}
}
