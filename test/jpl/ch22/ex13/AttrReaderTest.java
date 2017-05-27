package jpl.ch22.ex13;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import jpl.ch05.ex01.Attributed;

public class AttrReaderTest {
	@Test
	public void testAttrReader() {
		new AttrReader();
	}

	@Test
	public void testReadAttrs() throws IOException {
		final StringReader buf = new StringReader("a=1\nb=2");
		final Attributed attrs = AttrReader.readAttrs(buf);

		assertThat(attrs.find("a").getValue(), is("1"));
		assertThat(attrs.find("b").getValue(), is("2"));
	}

	@Test
	public void testReadAttrsDelimInVal() throws IOException {
		final StringReader buf = new StringReader("a=1=2");
		final Attributed attrs = AttrReader.readAttrs(buf);

		assertThat(attrs.find("a").getValue(), is("1=2"));
	}

	@Test(expected = IOException.class)
	public void testReadAttrsNoName() throws IOException {
		final StringReader buf = new StringReader("=1");
		AttrReader.readAttrs(buf);
	}
}
