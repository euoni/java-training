package jpl.ch02.ex18;

import org.junit.Before;
import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;
import mockit.Deencapsulation;

public class VehicleTest {
	@Before
	public void setUp() {
		Deencapsulation.setField(Vehicle.class, "nextId", 0);
	}

	@Test
	public void testMainOne() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Vehicle.main(new String[] { "o1" });

		sc.stop();
		sc.assertEquals("Vehicle [id=0, speed=0.0, direction=0.0, owner=o1]");
	}

	@Test
	public void testMainTwo() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Vehicle.main(new String[] { "o1", "o2" });

		sc.stop();
		sc.assertEquals("Vehicle [id=0, speed=0.0, direction=0.0, owner=o1]",
				"Vehicle [id=1, speed=0.0, direction=0.0, owner=o2]");
	}
}
