package jpl.ch01.ex15;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayMutableLookupTest {

	@Test
	public void test() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		assertEquals(null, lookup.find("foo"));

		lookup.add("foo", "FOO");
		assertEquals("FOO", lookup.find("foo"));

		lookup.add("bar", "BAR");
		assertEquals("FOO", lookup.remove("foo"));
		assertEquals(null, lookup.remove("foo"));
		assertEquals(null, lookup.find("foo"));
		assertEquals("BAR", lookup.find("bar"));
	}

}
