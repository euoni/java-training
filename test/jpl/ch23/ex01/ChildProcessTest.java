package jpl.ch23.ex01;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ChildProcessTest {
	@Test
	public void testChildProcess() {
		new ChildProcess();
	}

	@Test
	public void testUserProg() throws IOException, InterruptedException {
		final InputStream stdin = System.in;

		try {
			// setup fake input
			final ByteArrayInputStream in = new ByteArrayInputStream("abc\n".getBytes());
			System.setIn(in);

			final StdoutCapture sc = new StdoutCapture();
			sc.start();

			final Process proc = ChildProcess.userProg("cat");

			// wait until fake input consumed
			// ByteArrayInputStream is synchronized, so this call is safe
			while (in.available() > 0)
				; // spin

			proc.getOutputStream().close();

			proc.waitFor();

			// close pipes
			proc.getInputStream().close();
			proc.getErrorStream().close();

			sc.stop();
			sc.assertEquals("abc");
		} finally {
			System.setIn(stdin);
		}
	}
}
