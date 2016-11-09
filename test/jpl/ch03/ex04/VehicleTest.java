package jpl.ch03.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch02.ex13.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void testGetId() {
		final Vehicle v0 = new Vehicle();
		assertThat(v0.getId(), is(0));

		final Vehicle v1 = new Vehicle();
		assertThat(v1.getId(), is(1));
	}
}
