package jpl.ch02.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void test() {
		final Vehicle vehicle0 = new Vehicle();
		final Vehicle vehicle1 = new Vehicle();

		assertThat(vehicle0.id, is(0));
		assertThat(vehicle1.id, is(1));
	}
}
