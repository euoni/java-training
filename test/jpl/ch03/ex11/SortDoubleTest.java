package jpl.ch03.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SortDoubleTest {
	@Test
	public void testProbe() {
		final SortDouble sort = new SortDouble() {
			@Override
			protected void doSort() {
				assertThat(probe(0), is(1.));
			}
		};

		sort.sort(new double[] { 1. });
	}
}
