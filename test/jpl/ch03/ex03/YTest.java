package jpl.ch03.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class YTest {
	@Test
	public void testMask() {
		final Y y = new Y();
		assertThat(y.mask(0x1111), is(0x1111));
	}
}
