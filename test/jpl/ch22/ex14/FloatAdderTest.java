package jpl.ch22.ex14;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class FloatAdderTest {
	@Test
	public void testFloatAdder() {
		new FloatAdder();
	}

	@Test
	public void testSumSingle() {
		assertThat(FloatAdder.sum("0"), is(0.));
		assertThat(FloatAdder.sum("0."), is(0.));
		assertThat(FloatAdder.sum("0.0"), is(0.));
		assertThat(FloatAdder.sum("0e1"), is(0.));
	}

	@Test
	public void testSumMulti() {
		assertThat(FloatAdder.sum("0 1"), is(1.));
		assertThat(FloatAdder.sum("0\n1"), is(1.));
		assertThat(FloatAdder.sum("0 1 2"), is(3.));
	}
}
