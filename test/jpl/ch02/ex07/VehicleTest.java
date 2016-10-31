package jpl.ch02.ex07;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import jpl.ch02.ex04.VehicleTestHelper;

public class VehicleTest extends VehicleTestHelper {
	@Test
	public void test() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Vehicle.main(null);

		sc.stop();
		sc.assertEquals("car.id = 0", "car.speed = 60.0", "car.direction = 180.0", "car.owner = Java", "train.id = 1",
				"train.speed = 120.0", "train.direction = 0.0", "train.owner = JR");
	}
}
