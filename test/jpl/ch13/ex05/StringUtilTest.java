package jpl.ch13.ex05;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {
	@Test
	public void testStringUtil() {
		new StringUtil();
	}

	@Test
	public void testInsertComma() {
		assertThat(StringUtil.insertComma("1543729"), is("1,543,729"));
		assertThat(StringUtil.insertComma("154372"), is("154,372"));
		assertThat(StringUtil.insertComma("154"), is("154"));
	}
}
