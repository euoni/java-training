package jpl.ch07.ex03;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class PascalsTriangleTest {
	@Test
	public void testPascalsTriangle() {
		new PascalsTriangle();
		return;
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		PascalsTriangle.main(null);

		sc.stop();
		sc.assertEquals(new String[] { "n=12", "                         1", "                       1   1",
				"                     1   2   1", "                   1   3   3   1",
				"                 1   4   6   4   1", "               1   5  10  10   5   1",
				"             1   6  15  20  15   6   1", "           1   7  21  35  35  21   7   1",
				"         1   8  28  56  70  56  28   8   1", "       1   9  36  84 126 126  84  36   9   1",
				"     1  10  45 120 210 252 210 120  45  10   1", "   1  11  55 165 330 462 462 330 165  55  11   1",
				"n=15", "                                       1", "                                    1    1",
				"                                  1    2    1", "                               1    3    3    1",
				"                             1    4    6    4    1",
				"                          1    5   10   10    5    1",
				"                        1    6   15   20   15    6    1",
				"                     1    7   21   35   35   21    7    1",
				"                   1    8   28   56   70   56   28    8    1",
				"                1    9   36   84  126  126   84   36    9    1",
				"              1   10   45  120  210  252  210  120   45   10    1",
				"           1   11   55  165  330  462  462  330  165   55   11    1",
				"         1   12   66  220  495  792  924  792  495  220   66   12    1",
				"      1   13   78  286  715 1287 1716 1716 1287  715  286   78   13    1",
				"    1   14   91  364 1001 2002 3003 3432 3003 2002 1001  364   91   14    1", });
	}
}