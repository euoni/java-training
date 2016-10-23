package jpl.ch02.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {
	@Before
	public void setUp() {
		Vehicle.nextId = 0;
	}

	@Test
	public void testToString() {
		final Vehicle v = new Vehicle();
		v.speed = 60.;
		v.direction = 360.;
		v.owner = "OWNER";

		assertThat(v.toString(), is("Vehicle [id=0, speed=60.0, direction=360.0, owner=OWNER]"));
	}
}
