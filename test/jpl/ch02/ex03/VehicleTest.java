package jpl.ch02.ex03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		final Vehicle vehicle = new Vehicle();
		vehicle.id = Vehicle.nextId++;
		vehicle.speed = 30.0;
		vehicle.direction = 0;
		vehicle.owner = "Java";

		assertEquals(1, Vehicle.nextId);
	}

}
