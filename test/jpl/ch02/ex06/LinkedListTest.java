package jpl.ch02.ex06;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
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
		LinkedList.main(null);

		final String expected = String.join(System.lineSeparator(),
				new String[] { "head.value.id = 0", "head.value.speed = 60.0", "head.value.direction = 180.0",
						"head.value.owner = Java", "head.next.value.id = 1", "head.next.value.speed = 120.0",
						"head.next.value.direction = 0.0", "head.next.value.owner = JR", });
		assertEquals(expected + System.lineSeparator(), outContent.toString());
	}
}
