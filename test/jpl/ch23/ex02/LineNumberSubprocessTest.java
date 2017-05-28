package jpl.ch23.ex02;

import java.io.IOException;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class LineNumberSubprocessTest {
	@Test
	public void testLineNumberSubprocess() {
		new LineNumberSubprocess();
	}

	@Test
	public void testMain() throws IOException, InterruptedException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		LineNumberSubprocess.main(new String[] { "printf", "a\\nb\\nc" });

		sc.stop();
		sc.assertEquals("   1	a", "   2	b", "   3	c");
	}
}
