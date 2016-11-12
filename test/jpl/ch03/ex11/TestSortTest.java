package jpl.ch03.ex11;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class TestSortTest {
	@Test
	public void testTestSort() {
		new TestSort();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		TestSort.main(null);

		sc.stop();
		sc.assertEquals("Sort: jpl.ch03.ex11.EvilSimpleSortDouble1", "	Metrics: 0 probes 0 comapres 0 swaps",
				"		0.013", "		0.3", "		3.17", "		7.9", "Sort: jpl.ch03.ex11.EvilSimpleSortDouble2",
				"	Metrics: 0 probes 0 comapres 0 swaps", "		0.013", "		0.3", "		3.17", "		7.9");
	}
}
