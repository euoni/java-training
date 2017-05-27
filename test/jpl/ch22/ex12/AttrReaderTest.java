package jpl.ch22.ex12;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.StringReader;

import org.junit.Test;

import jpl.ch05.ex01.Attributed;

public class AttrReaderTest {
	@Test
	public void testAttrReader() {
		new AttrReader();
	}

	@Test
	public void testReadAttrs() {
		final StringReader buf = new StringReader("a=1\nb=2");
		final Attributed attrs = AttrReader.readAttrs(buf);

		assertThat(attrs.find("a").getValue(), is("1"));
		assertThat(attrs.find("b").getValue(), is("2"));
	}
}
