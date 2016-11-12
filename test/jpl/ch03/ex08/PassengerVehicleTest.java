package jpl.ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTest extends VehicleTestHelper {
	@Test
	public void testClone() throws CloneNotSupportedException {
		final PassengerVehicle v1 = new PassengerVehicle(5);
		v1.setSpeed(100.);
		v1.setPassengers(2);

		final PassengerVehicle v2 = v1.clone();

		assertThat(v2.getId(), is(1));
		assertThat(v2.getSpeed(), is(100.));
		assertThat(v2.getSeats(), is(5));
		assertThat(v2.getPassengers(), is(2));
	}
}
