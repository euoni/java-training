package jpl.ch02.ex01;

import org.junit.Test;

public class VehicleTest {
	@Test
	public void test() {
		final Vehicle vehicle = new Vehicle();
		vehicle.speed = 30.0;
		vehicle.direction = 0;
		vehicle.owner = "Java";

		return; // pass
	}
}
