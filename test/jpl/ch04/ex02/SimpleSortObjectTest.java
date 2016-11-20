package jpl.ch04.ex02;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch03.ex11.SortMetrics;

public class SimpleSortObjectTest {
	static Object[] testData = { 3L, 1, 2., "b", "a", new Object() };

	@Test
	public void testSort() {
		final Object[] value = testData.clone();

		final SimpleSortObject sort = new SimpleSortObject();
		final SortMetrics metrics = sort.sort(value);

		assertThat(value, arrayContaining(1, 2., 3L, "a", "b", testData[5]));
		assertThat(metrics.probeCnt, is(0));
		assertThat(metrics.compareCnt, is(15));
		assertThat(metrics.swapCnt, is(3));
	}
}
