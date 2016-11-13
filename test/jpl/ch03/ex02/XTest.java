package jpl.ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class XTest {
	@Test
	public void testMask() {
		final X x = new X();
		assertThat(x.mask(0x1111), is(0x0011));
	}
}
