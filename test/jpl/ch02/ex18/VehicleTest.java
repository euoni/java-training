package jpl.ch02.ex18;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import mockit.Deencapsulation;

public class VehicleTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUp() {
		Deencapsulation.setField(Vehicle.class, "nextId", 0);
	}

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testMainOne() {
		Vehicle.main(new String[] { "o1" });

		final String expected = String.join(System.lineSeparator(),
				new String[] { "Vehicle [id=0, speed=0.0, direction=0.0, owner=o1]", "" });
		assertEquals(expected, outContent.toString());
	}

	@Test
	public void testMainTwo() {
		Vehicle.main(new String[] { "o1", "o2" });

		final String expected = String.join(System.lineSeparator(),
				new String[] { "Vehicle [id=0, speed=0.0, direction=0.0, owner=o1]",
						"Vehicle [id=1, speed=0.0, direction=0.0, owner=o2]", "" });
		assertEquals(expected, outContent.toString());
	}
}
