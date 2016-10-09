package jpl.ch02.ex04;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		final Vehicle vehicle0 = new Vehicle();
		final Vehicle vehicle1 = new Vehicle();

		assertEquals(0, vehicle0.id);
		assertEquals(1, vehicle1.id);
	}

}
