package jpl.ch01.ex03;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class FibonacciTest {
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
		Fibonacci.main(null);

		String excepted = String.join(
			System.lineSeparator(),
			new String[] { "Fibonacci", "1", "1", "2", "3", "5", "8", "13", "21", "34" }
		);
		assertEquals(excepted + System.lineSeparator(), outContent.toString());
	}
}
