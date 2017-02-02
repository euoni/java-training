package jpl.ch13.ex04;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoaderTest {
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testLoader() {
		new Loader();
	}

	@Test
	public void testCount() {
		assertThat(Loader.load("String a b c"), contains("a b c"));
		assertThat(Loader.load("String"), contains((String) null));
		assertThat(Loader.load("Boolean 1\nByte 1"), contains(Boolean.parseBoolean("1"), Byte.parseByte("1")));
		assertThat(Loader.load("Character 123"), contains('1'));
	}

	@Test
	public void testCountExcption() {
		thrown.expect(UnsupportedOperationException.class);
		thrown.expectMessage("Type \"Hoge\" is not supported");
		Loader.load("Hoge");
	}
}
