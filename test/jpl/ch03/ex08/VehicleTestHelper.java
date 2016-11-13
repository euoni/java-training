package jpl.ch03.ex08;

import org.junit.Before;

public class VehicleTestHelper {
	@Before
	public void setUpVehicle() {
		Vehicle.nextId = 0;
	}
}
