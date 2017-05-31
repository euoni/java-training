package jpl.ch22.ex15;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

public class CalculatorTest {
	@Test
	public void testCalculator() {
		new Calculator();
	}

	@Test
	public void testCalc() {
		assertThat(Calculator.calc("0"), is(0.));
		assertThat(Calculator.calc("0 1 +"), is(0. + 1.));
		assertThat(Calculator.calc("0 1 -"), is(0. - 1.));
		assertThat(Calculator.calc("1 2 *"), is(1. * 2.));
		assertThat(Calculator.calc("1 2 /"), is(1. / 2.));
		assertThat(Calculator.calc("1 2 %"), is(1. % 2.));
		assertThat(Calculator.calc("1 5 + 2 3 + *"), is((1. + 5.) * (2. + 3.)));
		assertThat(Calculator.calc("PI sin"), is(Math.sin(Math.PI)));
		assertThat(Calculator.calc("PI cos"), is(Math.cos(Math.PI)));
		assertThat(Calculator.calc("PI round"), is(3.));
	}

	@Test(expected = IllegalStateException.class)
	public void testCalcError1() {
		Calculator.calc("0 1");
	}

	@Test(expected = NoSuchElementException.class)
	public void testCalcError2() {
		Calculator.calc("+");
	}

	@Test(expected = IllegalStateException.class)
	public void testCalcError3() {
		Calculator.calc("foo");
	}
}
