package jpl.ch01.ex12;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImprovedFibonacciTest {
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
		ImprovedFibonacci.main(null);

		final String expected = String.join(System.lineSeparator(),
				new String[] { "1: 1", "2: 1", "3: 2 *", "4: 3", "5: 5", "6: 8 *", "7: 13", "8: 21", "9: 34 *" });
		assertEquals(expected + System.lineSeparator(), outContent.toString());
	}
}
