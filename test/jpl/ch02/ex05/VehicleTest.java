package jpl.ch02.ex05;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VehicleTest {
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
		Vehicle.main(null);

		final String expected = String.join(System.lineSeparator(),
				new String[] { "car.id = 0", "car.speed = 60.0", "car.direction = 180.0", "car.owner = Java",
						"train.id = 1", "train.speed = 120.0", "train.direction = 0.0", "train.owner = JR" });
		assertEquals(expected + System.lineSeparator(), outContent.toString());
	}
}
