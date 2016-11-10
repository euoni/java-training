package jpl.ch03.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GasTankTest {
	@Test
	public void test() {
		final GasTank gas = new GasTank();
		assertThat(gas.getGas(), is(0.));
		assertThat(gas.empty(), is(true));

		gas.setGas(10.);

		assertThat(gas.getGas(), is(10.));
		assertThat(gas.empty(), is(false));
	}
}
