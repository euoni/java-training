package jpl.ch01.ex01;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class HelloWorldTest {
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
		HelloWorld.main(null);
		assertEquals("Hello, world" + System.lineSeparator(), outContent.toString());
	}
}
