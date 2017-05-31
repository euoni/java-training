package jpl.ch20.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class LineReaderTest {
	@Test
	public void testReadLine() throws IOException {
		final LineReader reader = new LineReader(new StringReader("abc\ndef"));

		assertThat(reader.readLine(), is("abc\n"));
		assertThat(reader.readLine(), is("def"));
		assertThat(reader.readLine(), is(""));

		reader.close();
	}
}
