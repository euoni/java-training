package jpl.ch02.ex13;

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
	public void testVehicle() {
		final Vehicle v1 = new Vehicle();
		assertThat(v1.getId(), is(0));

		final Vehicle v2 = new Vehicle();
		assertThat(v2.getId(), is(1));
	}

	@Test
	public void testSpeed() {
		final Vehicle v = new Vehicle();
		assertThat(v.getSpeed(), is(0.));

		final double speed = 100.;
		v.setSpeed(speed);
		assertThat(v.getSpeed(), is(speed));
	}

	@Test
	public void testDirection() {
		final Vehicle v = new Vehicle();
		assertThat(v.getDirection(), is(0.));

		final double direction = 180.;
		v.setDirection(direction);
		assertThat(v.getDirection(), is(direction));
	}

	@Test
	public void testOwner() {
		final Vehicle v = new Vehicle();
		assertThat(v.getOwner(), nullValue());

		final String owner = "owner";
		v.setOwner(owner);
		assertThat(v.getOwner(), is(owner));
	}

	@Test
	public void testGetMaxId() {
		assertThat(Vehicle.getMaxId(), is(-1));

		new Vehicle();
		assertThat(Vehicle.getMaxId(), is(0));
	}

	@Test
	public void testToString() {
		final Vehicle v = new Vehicle();
		v.setSpeed(60.);
		v.setDirection(360.);
		v.setOwner("OWNER");

		assertThat(v.toString(), is("Vehicle [id=0, speed=60.0, direction=360.0, owner=OWNER]"));
	}
}