package jpl.ch01.ex14;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WalkmanTest {
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
		final Walkman walkman = new Walkman();
		walkman.setTape(music);
		walkman.play();

		assertEquals(music + System.lineSeparator(), outContent.toString());
	}
}
