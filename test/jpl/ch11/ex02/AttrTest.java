package jpl.ch11.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {
	@Test
	public void testAttrString() {
		final Attr<String> attr = new Attr<>("attr");

		assertThat(attr.getName(), is("attr"));
		assertThat(attr.getValue(), nullValue());
	}

	@Test
	public void testAttrStringObject() {
		final Attr<String> attr = new Attr<>("attr", "value");

		assertThat(attr.getName(), is("attr"));
		assertThat(attr.getValue(), is("value"));
	}

	@Test
	public void testSetValue() {
		final Attr<String> attr = new Attr<>("attr");
		attr.setValue("value");

		assertThat(attr.getValue(), is("value"));
	}

	@Test
	public void testToString() {
		final Attr<String> attr = new Attr<>("attr", "value");
		assertThat(attr.toString(), is("attr='value'"));
	}
}
