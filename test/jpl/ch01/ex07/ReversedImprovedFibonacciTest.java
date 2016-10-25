package jpl.ch01.ex07;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ReversedImprovedFibonacciTest {
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
	public void testMain() {
		ReversedImprovedFibonacci.main(null);

		final String expected = String.join(System.lineSeparator(),
				new String[] { "9: 1", "8: 1", "7: 2 *", "6: 3", "5: 5", "4: 8 *", "3: 13", "2: 21", "1: 34 *" });
		assertEquals(expected + System.lineSeparator(), outContent.toString());
	}
}
