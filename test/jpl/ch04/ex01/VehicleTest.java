package jpl.ch04.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch02.ex13.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void testVehicleEnergySource() {
		final Vehicle gasVehicle = new Vehicle(new GasTank());
		assertThat(gasVehicle.getId(), is(0));

		final Vehicle batteryVehicle = new Vehicle(new Battery());
		assertThat(batteryVehicle.getId(), is(1));
	}

	@Test
	public void testGetSource() {
		final GasTank gas = new GasTank();
		final Vehicle gasVehicle = new Vehicle(gas);

		assertThat(gasVehicle.getSource(), is(gas));
	}

	@Test
	public void testStartGasTank() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final GasTank gas = new GasTank();
		gas.setGas(20.);

		final Vehicle vehicle = new Vehicle(gas);
		vehicle.start();

		sc.stop();
		sc.assertEquals("start");
	}

	@Test(expected = IllegalStateException.class)
	public void testStartGasTankException() {
		final Vehicle vehicle = new Vehicle(new GasTank());
		vehicle.start();
	}

	@Test
	public void testStartBatteryTank() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final Battery battery = new Battery();
		battery.setPower(100.);

		final Vehicle vehicle = new Vehicle(battery);
		vehicle.start();

		sc.stop();
		sc.assertEquals("start");
	}

	@Test(expected = IllegalStateException.class)
	public void testStartBatteryException() {
		final Vehicle vehicle = new Vehicle(new Battery());
		vehicle.start();
	}
}
