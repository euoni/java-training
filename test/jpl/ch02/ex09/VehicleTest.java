package jpl.ch02.ex09;

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
		assertThat(Deencapsulation.getField(v1, "id"), is(0));
		assertThat(Deencapsulation.getField(Vehicle.class, "nextId"), is(1));

		final Vehicle v2 = new Vehicle();
		assertThat(Deencapsulation.getField(v2, "id"), is(1));
		assertThat(Deencapsulation.getField(Vehicle.class, "nextId"), is(2));
	}

	@Test
	public void testVehicleString() {
		final String owner = "owner";
		final Vehicle v = new Vehicle(owner);
		assertThat(Deencapsulation.getField(v, "owner"), is(owner));
	}

	@Test
	public void testGetSpeed() {
		final Vehicle v = new Vehicle();
		assertThat(v.getSpeed(), is(0.0));

		final double speed = 100;
		Deencapsulation.setField(v, "speed", speed);

		assertThat(v.getSpeed(), is(speed));
	}

	@Test
	public void testSetSpeed() {
		final Vehicle v = new Vehicle();

		final double speed = 100;
		v.setSpeed(speed);

		assertThat(Deencapsulation.getField(v, "speed"), is(speed));
	}

	@Test
	public void testGetDirection() {
		final Vehicle v = new Vehicle();
		assertThat(v.getDirection(), is(0.0));

		final double direction = 180;
		Deencapsulation.setField(v, "direction", direction);

		assertThat(v.getDirection(), is(direction));
	}

	@Test
	public void testSetDirection() {
		final Vehicle v = new Vehicle();

		final double direction = 180;
		v.setDirection(direction);

		assertThat(Deencapsulation.getField(v, "direction"), is(direction));
	}

	@Test
	public void testGetOwner() {
		final Vehicle v = new Vehicle();
		assertThat(v.getOwner(), nullValue());

		final String owner = "owner";
		Deencapsulation.setField(v, "owner", owner);

		assertThat(v.getOwner(), is(owner));
	}

	@Test
	public void testSetOwner() {
		final Vehicle v = new Vehicle();

		final String owner = "owner";
		v.setOwner(owner);

		assertThat(Deencapsulation.getField(v, "owner"), is(owner));
	}

	@Test
	public void testGetId() {
		final Vehicle v1 = new Vehicle();
		assertThat(v1.getId(), is(0));

		final Vehicle v2 = new Vehicle();
		assertThat(v2.getId(), is(1));
	}

	@Test
	public void testGetMaxId() {
		assertThat(Vehicle.getMaxId(), is(-1));

		new Vehicle();
		assertThat(Vehicle.getMaxId(), is(0));
	}

}
