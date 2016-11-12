package jpl.ch03.ex11;

import org.junit.Test;

public class EvilSimpleSafeSortDouble1Test {
	@Test(expected = IllegalStateException.class)
	public void testSort() {
		final EvilSimpleSafeSortDouble1 sort = new EvilSimpleSafeSortDouble1();
		sort.sort(new double[] { 3., 1., 2. });
	}
}
