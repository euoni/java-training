package jpl.ch24.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.MissingResourceException;

import org.junit.Test;

public class GlobalRes_deTest {
	@Test(expected = MissingResourceException.class)
	public void testHandleGetObjectString() {
		final GlobalRes_de res = new GlobalRes_de();
		res.getString("no");
	}

	@Test
	public void testGetKeys() {
		final GlobalRes_de res = new GlobalRes_de();
		assertThat(res.containsKey("hello"), is(true));
		assertThat(res.containsKey("goodbye"), is(true));
		assertThat(res.containsKey("no"), is(false));
	}
}
