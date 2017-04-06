package jpl.ch20.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

public class EncryptOutputStreamTest {
	@Test
	public void testWrite() throws IOException {
		final ByteArrayOutputStream buf = new ByteArrayOutputStream();
		final EncryptOutputStream output = new EncryptOutputStream(buf);

		output.write("HELLO".getBytes());
		assertThat(buf.toString(), is("URYY\\"));

		output.close();
	}
}
