package jpl.ch01.ex04;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SquareTableTest {
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
		SquareTable.main(null);

		final String expected = String.join(System.lineSeparator(),
				new String[] { "11 * 11 = 121", "12 * 12 = 144", "13 * 13 = 169", "14 * 14 = 196", "15 * 15 = 225",
						"16 * 16 = 256", "17 * 17 = 289", "18 * 18 = 324", "19 * 19 = 361", "20 * 20 = 400" });
		assertEquals(expected + System.lineSeparator(), outContent.toString());
	}
}
