package jpl.ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void testClone() throws CloneNotSupportedException {
		final Vehicle v1 = new Vehicle();
		v1.setSpeed(100.);

		final Vehicle v2 = v1.clone();

		assertThat(v2.getId(), is(1));
		assertThat(v2.getSpeed(), is(100.));
	}

	@Test
	public void testToString() {
		final Vehicle v = new Vehicle();

		assertThat(v.toString(), is("Vehicle [id=0, speed=0.0]"));
	}
}
