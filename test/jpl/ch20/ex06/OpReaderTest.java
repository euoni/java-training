package jpl.ch20.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class OpReaderTest {
	@Test
	public void testRead() throws IOException {
		final OpReader reader = new OpReader();

		reader.read(new StringReader("a + 5"));
		assertThat(reader.getMap().get("a"), is(5.));

		reader.read(new StringReader("a - 2"));
		assertThat(reader.getMap().get("a"), is(3.));
		
		reader.read(new StringReader("a = 1"));
		assertThat(reader.getMap().get("a"), is(1.));
	}
}
