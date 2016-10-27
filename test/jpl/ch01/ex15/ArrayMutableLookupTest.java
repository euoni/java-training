package jpl.ch01.ex15;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayMutableLookupTest {
	@Test
	public void test() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		assertThat(lookup.find("foo"), nullValue());

		lookup.add("foo", "FOO");
		assertThat(lookup.find("foo"), is("FOO"));

		lookup.add("bar", "BAR");
		assertThat(lookup.remove("foo"), is("FOO"));
		assertThat(lookup.remove("foo"), nullValue());
		assertThat(lookup.find("foo"), nullValue());
		assertThat(lookup.find("bar"), is("BAR"));
	}
}
