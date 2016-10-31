package jpl.ch02.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch02.ex04.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void testToString() {
		final Vehicle v = new Vehicle();
		v.speed = 60.;
		v.direction = 360.;
		v.owner = "OWNER";

		assertThat(v.toString(), is("Vehicle [id=0, speed=60.0, direction=360.0, owner=OWNER]"));
	}
}
