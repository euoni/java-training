package jpl.ch04.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SortObjectTest {
	@Test
	public void testProbe() {
		final SortObject sort = new SortObject() {
			@Override
			protected void doSort() {
				assertThat(probe(0), is("1"));
			}
		};

		sort.sort(new Object[] { 1 });
	}
}
