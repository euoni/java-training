package jpl.ch03.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {
	@Test
	public void testAttrString() {
		final Attr attr = new Attr("attr");

		assertThat(attr.getName(), is("attr"));
		assertThat(attr.getValue(), nullValue());
	}

	@Test
	public void testAttrStringObject() {
		final Attr attr = new Attr("attr", "value");

		assertThat(attr.getName(), is("attr"));
		assertThat(attr.getValue(), is("value"));
	}

	@Test
	public void testSetValue() {
		final Attr attr = new Attr("attr");
		attr.setValue("value");

		assertThat(attr.getValue(), is("value"));
	}

	@Test
	public void testToString() {
		final Attr attr = new Attr("attr", "value");
		assertThat(attr.toString(), is("attr='value'"));
	}

	@Test
	public void testHashCode() {
		final Attr x1 = new Attr("x", "x");
		final Attr x2 = new Attr("x", "x");
		final Attr y1 = new Attr(null);
		final Attr y2 = new Attr(null);

		assertThat(x1.hashCode(), is(x2.hashCode()));
		assertThat(y1.hashCode(), is(y2.hashCode()));
	}

	@Test
	public void testEquals() {
		final Attr x1 = new Attr("x", "x");
		final Attr x2 = new Attr("x", "x");
		final Attr y1 = new Attr(null);
		final Attr y2 = new Attr(null);
		final Attr z1 = new Attr(null, "z");
		final Attr w1 = new Attr(null, "w");

		assertThat(x1, is(x1));

		assertThat(x1, is(x2));
		assertThat(x2, is(x1));

		assertThat(y1, is(y2));
		assertThat(y2, is(y1));

		assertThat(x1, not(is(y1)));
		assertThat(y1, not(is(x1)));

		assertThat(y1, not(is(z1)));

		assertThat(x1, not(is((Attr) null)));

		assertThat(x1, not(is(0)));

		assertThat(z1, not(is(w1)));
	}
}
