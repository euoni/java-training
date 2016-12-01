package jpl.ch06.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch02.ex13.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
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

		v.turn(Vehicle.TurnDirection.LEFT);
		assertThat(v.getDirection(), is(-90.));

		v.turn(Vehicle.TurnDirection.RIGHT);
		assertThat(v.getDirection(), is(0.));
	}
}
