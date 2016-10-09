package jpl.ch01.ex14;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DualWalkmanTest {
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
	public void test() {
		final String music = "music";
		final DualChatWalkman walkman = new DualChatWalkman();
		walkman.setTape(music);
		walkman.play();

		final String expected = String.join(System.lineSeparator(), new String[] { "1: " + music, "2: " + music });
		assertEquals(expected + System.lineSeparator(), outContent.toString());
	}
}
