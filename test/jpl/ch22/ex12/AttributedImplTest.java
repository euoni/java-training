package jpl.ch22.ex12;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import jpl.ch05.ex01.Attributed.Attr;

public class AttributedImplTest {
	@Test
	public void testAddFindRemove() {
		final AttributedImpl attrs = new AttributedImpl();
		assertThat(attrs.remove("name"), nullValue());
		assertThat(attrs.find("name"), nullValue());

		final AttributedImpl.Attr attr = new AttributedImpl.Attr("name");
		attrs.add(attr);
		assertThat(attrs.find("name"), is(attr));

		assertThat(attrs.remove("name"), is(attr));

		assertThat(attrs.find("name"), nullValue());
	}

	@Test
	public void testAttrs() {
		final AttributedImpl attrs = new AttributedImpl();
		assertThat(attrs.attrs().hasNext(), is(false));

		final AttributedImpl.Attr attr = new AttributedImpl.Attr("name");
		attrs.add(attr);

		final Iterator<Attr> iterator = attrs.attrs();
		assertThat(iterator.hasNext(), is(true));
		assertThat(iterator.next(), is(attr));
		assertThat(iterator.hasNext(), is(false));
	}
}
