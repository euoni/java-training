package jpl.ch03.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch02.ex13.VehicleTestHelper;

public class PassengerVehicleTest extends VehicleTestHelper {
	@Test
	public void testToString() {
		final PassengerVehicle vehicle = new PassengerVehicle(5);

		assertThat(vehicle.toString(), is(
				"PassengerVehicle [seats=5, passengers=0, getSpeed()=0.0, getDirection()=0.0, getOwner()=null, getId()=0]"));
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		PassengerVehicle.main(null);

		sc.stop();
		sc.assertEquals(
				"PassengerVehicle [seats=5, passengers=0, getSpeed()=0.0, getDirection()=0.0, getOwner()=null, getId()=0]",
				"PassengerVehicle [seats=7, passengers=0, getSpeed()=0.0, getDirection()=0.0, getOwner()=null, getId()=1]");
	}

	@Test
	public void testPassengerVehicle() {
		final PassengerVehicle vehicle = new PassengerVehicle(5);

		assertThat(vehicle.getSeats(), is(5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPassengerVehicleException() {
		new PassengerVehicle(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPassengersException1() {
		new PassengerVehicle(1).setPassengers(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPassengersException2() {
		new PassengerVehicle(1).setPassengers(2);
	}

	@Test
	public void testSetPassengers() {
		final PassengerVehicle vehicle = new PassengerVehicle(5);
		assertThat(vehicle.getPassengers(), is(0));

		vehicle.setPassengers(2);
		assertThat(vehicle.getPassengers(), is(2));
	}
}
