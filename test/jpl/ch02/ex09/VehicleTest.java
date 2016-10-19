package jpl.ch02.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {
	@Before
	public void setUp() {
		Vehicle.nextId = 0;
	}

	@Test
	public void testGetMaxId() {
		assertThat(Vehicle.getMaxId(), is(-1));

		new Vehicle();
		assertThat(Vehicle.getMaxId(), is(0));
	}
}
