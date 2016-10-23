package jpl.ch02.ex17;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {
	@Test
	public void testTurnDouble() {
		final Vehicle v = new Vehicle();

		v.turn(-90.);
		assertThat(v.getDirection(), is(-90.));

		v.turn(90.);
		assertThat(v.getDirection(), is(0.));
	}

	@Test
	public void testTurnChar() {
		final Vehicle v = new Vehicle();

		v.turn(Vehicle.TURN_LEFT);
		assertThat(v.getDirection(), is(-90.));

		v.turn(Vehicle.TURN_RIGHT);
		assertThat(v.getDirection(), is(0.));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTurnCharException() {
		final Vehicle v = new Vehicle();
		v.turn('_');
	}
}
