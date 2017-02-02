package jpl.ch13.ex06;

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
		assertThat(StringUtil.insertComma("1543729", ",", 3), is("1,543,729"));
		assertThat(StringUtil.insertComma("154372", ",", 3), is("154,372"));
		assertThat(StringUtil.insertComma("154", ",", 3), is("154"));

		assertThat(StringUtil.insertComma("1543729", "##", 2), is("1##54##37##29"));
		assertThat(StringUtil.insertComma("154372", "##", 2), is("15##43##72"));
		assertThat(StringUtil.insertComma("154", "##", 2), is("1##54"));
	}
}
