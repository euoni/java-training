package jpl.ch03.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SafeSortDoubleTest {
	@Test
	public void testSort() {
		final SafeSortDouble sort = new SafeSortDouble() {
			@Override
			protected void doSort() {
				for (int i = 0; i < getDataLength(); i++) {
					for (int j = i + 1; j < getDataLength(); j++) {
						if (compare(i, j) > 0)
							swap(i, j);
					}
				}
			}
		};

		final double[] value = new double[] { 3., 1., 2. };
		sort.sort(value);

		assertThat(value[0], is(1.));
		assertThat(value[1], is(2.));
		assertThat(value[2], is(3.));
	}

	@Test
	public void testProbe() {
		final SafeSortDouble sort = new SafeSortDouble() {
			@Override
			protected void doSort() {
				assertThat(probe(0), is(1.));
			}
		};

		sort.sort(new double[] { 1. });
	}

	@Test(expected = IllegalStateException.class)
	public void testProbeException() {
		final SafeSortDouble sort = new SafeSortDouble() {
			@Override
			protected void doSort() {
				for (long i = 0; i < 0x80000000L; i++) {
					probe(0);
				}
			}
		};

		sort.sort(new double[] { 1. });
	}
}
