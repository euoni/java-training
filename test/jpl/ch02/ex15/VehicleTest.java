package jpl.ch02.ex15;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch02.ex13.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void testChangeSpeed() {
		final Vehicle v = new Vehicle();

		final double speed = 100.;
		v.changeSpeed(speed);
		assertThat(v.getSpeed(), is(speed));
	}

	@Test
	public void testStop() {
		final Vehicle v = new Vehicle();
		v.setSpeed(100.);

		v.stop();
		assertThat(v.getSpeed(), is(0.));
	}
}
