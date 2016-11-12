package jpl.ch03.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch03.ex08.Vehicle;
import jpl.ch03.ex08.VehicleTestHelper;

public class GarageTest extends VehicleTestHelper {
	@Test
	public void testLeave() {
		final Vehicle v = new Vehicle();
		final Garage garage = new Garage(1);
		garage.enter(v);

		assertThat(garage.leave(), is(v));
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Garage.main(null);

		sc.stop();
		sc.assertEquals("Garage [buffer=[Vehicle [id=0, speed=10.0], Vehicle [id=1, speed=20.0], null, null, null]]",
				"Garage [buffer=[Vehicle [id=2, speed=10.0], Vehicle [id=3, speed=20.0], null, null, null]]");
	}
}
