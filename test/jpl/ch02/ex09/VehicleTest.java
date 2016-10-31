package jpl.ch02.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch02.ex04.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void testGetMaxId() {
		assertThat(Vehicle.getMaxId(), is(-1));

		new Vehicle();
		assertThat(Vehicle.getMaxId(), is(0));
	}
}
