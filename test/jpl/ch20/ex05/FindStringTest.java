package jpl.ch20.ex05;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FindStringTest {
	@Test
	public void testFind() throws IOException {
		final StringReader str = new StringReader("abc def\n" + "ghi jkl\n" + "abc 000");

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		new FindString("abc").find(str);

		sc.stop();
		sc.assertEquals("1 abc def", "3 abc 000");
	}
}
