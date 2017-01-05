package jpl.ch10.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class EscapeTest {
	@Test
	public void testEscape() {
		new Escape();
		return;
	}

	@Test
	public void testEscapeString() {
		assertThat(Escape.escapeString("\n\t\b\r\f\\\'\""), is("\\\n\\\t\\\b\\\r\\\f\\\\\\\'\\\""));
	}
}
