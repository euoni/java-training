package jpl.ch04.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class BatteryTest {
	@Test
	public void test() {
		final Battery battery = new Battery();
		assertThat(battery.getPower(), is(0.));
		assertThat(battery.empty(), is(true));

		battery.setPower(100.);

		assertThat(battery.getPower(), is(100.));
		assertThat(battery.empty(), is(false));
	}
}
