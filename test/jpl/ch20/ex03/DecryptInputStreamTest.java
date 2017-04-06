package jpl.ch20.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.Test;

public class DecryptInputStreamTest {
	@Test
	public void testRead() throws IOException {
		final ByteArrayInputStream buf = new ByteArrayInputStream(new byte[] { 0 });
		final DecryptInputStream input = new DecryptInputStream(buf);

		assertThat(input.read(), is(243));

		input.close();
	}

	@Test
	public void testReadArray() throws IOException {
		final ByteArrayInputStream buf = new ByteArrayInputStream("URYY\\".getBytes());
		final DecryptInputStream input = new DecryptInputStream(buf);

		final byte[] read = new byte[5];
		input.read(read);
		assertThat(new String(read), is("HELLO"));

		input.close();
	}
}
