package jpl.ch03.ex11;

import org.junit.Test;

public class EvilSimpleSafeSortDouble2Test {
	@Test(expected = IllegalStateException.class)
	public void testSort() {
		final EvilSimpleSafeSortDouble2 sort = new EvilSimpleSafeSortDouble2();
		sort.sort(new double[] { 3., 1., 2. });
	}
}
