package jpl.ch20.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class TranslateByteTest {
	@Test
	public void testMain() throws IOException {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final String data = "Hello world";
		final InputStream stdin = System.in;
		try {
			System.setIn(new ByteArrayInputStream(data.getBytes()));

			TranslateByte.main(new String[] { "l", "L" });
		} finally {
			System.setIn(stdin);
		}

		sc.stop();
		sc.assertEquals("HeLLo worLd");
	}

	@Test
	public void testReadArray() throws IOException {
		final ByteArrayInputStream buf = new ByteArrayInputStream("Hello world".getBytes());
		final TranslateByte input = new TranslateByte(buf, (byte) 'l', (byte) 'L');

		final byte[] read = new byte[11];
		input.read(read);
		assertThat(new String(read), is("HeLLo worLd"));

		input.close();
	}
}
