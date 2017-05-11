package jpl.ch22.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class WhichCharsTest {
	@Test
	public void testToString() {
		final WhichChars chars = new WhichChars("Testing 123");

		assertThat(chars.toString(), is("[ 123Teginst]"));
	}
}
