package jpl.ch02.ex04;

import org.junit.Before;

public class VehicleTestHelper {
	@Before
	public void setUpVehicle() {
		Vehicle.nextId = 0;
	}
}
