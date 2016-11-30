package jpl.ch05.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AttributedTest {
	@Test
	public void testAttrString() {
		final Attributed.Attr attr = new Attributed.Attr("attr");

		assertThat(attr.getName(), is("attr"));
		assertThat(attr.getValue(), nullValue());
	}

	@Test
	public void testAttrStringObject() {
		final Attributed.Attr attr = new Attributed.Attr("attr", "value");

		assertThat(attr.getName(), is("attr"));
		assertThat(attr.getValue(), is("value"));
	}

	@Test
	public void testSetValue() {
		final Attributed.Attr attr = new Attributed.Attr("attr");
		attr.setValue("value");

		assertThat(attr.getValue(), is("value"));
	}
}
