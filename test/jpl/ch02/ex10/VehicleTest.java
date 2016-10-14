package jpl.ch02.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import mockit.Deencapsulation;

public class VehicleTest {

	@Before
	public void setUp() throws Exception {
		Deencapsulation.setField(Vehicle.class, "nextId", 0);
	}

	@Test
	public void testToString() {
		final Vehicle v = new Vehicle();
		v.setSpeed(60);
		v.setDirection(360);
		v.setOwner("OWNER");

		assertThat(v.toString(), is("Vehicle [id=0, speed=60.0, direction=360.0, owner=OWNER]"));
	}

}
