package jpl.ch02.ex13;

import org.junit.Before;

public class VehicleTestHelper {
	@Before
	public void setUpVehicle() {
		Vehicle.nextId = 0;
	}
}
