package jpl.ch01.ex15;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ArrayMutableLookupTest {
	@Test
	public void testFindNull() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();

		assertThat(lookup.find("foo"), nullValue());
	}

	@Test
	public void testAddFind1() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		lookup.add("foo", "FOO");

		assertThat(lookup.find("foo"), is("FOO"));
	}

	@Test
	public void testAddFind2() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		lookup.add("foo", "FOO");
		lookup.add("bar", "BAR");

		assertThat(lookup.find("foo"), is("FOO"));
		assertThat(lookup.find("bar"), is("BAR"));
	}

	@Test
	public void testRemoveNull() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();

		assertThat(lookup.remove("foo"), is(false));
	}

	@Test
	public void testRemove1() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		lookup.add("foo", "FOO");

		assertThat(lookup.remove("foo"), is(true));
		assertThat(lookup.remove("foo"), is(false));
	}

	@Test
	public void testRemove2() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		lookup.add("foo", "FOO");
		lookup.add("bar", "BAR");

		assertThat(lookup.remove("foo"), is(true));
		assertThat(lookup.remove("foo"), is(false));
		assertThat(lookup.remove("bar"), is(true));
		assertThat(lookup.remove("bar"), is(false));
	}

	@Test
	public void testAddDouble() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		lookup.add("foo", "FOO1");
		lookup.add("foo", "FOO2");

		assertThat(lookup.find("foo"), is("FOO1"));
	}

	@Test
	public void testAddFindRemoveDouble() {
		final ArrayMutableLookup lookup = new ArrayMutableLookup();
		lookup.add("foo", "FOO1");
		lookup.add("foo", "FOO2");

		assertThat(lookup.find("foo"), is("FOO1"));
		assertThat(lookup.remove("foo"), is(true));
		assertThat(lookup.find("foo"), is("FOO2"));
		assertThat(lookup.remove("foo"), is(true));
		assertThat(lookup.remove("foo"), is(false));
	}
}
