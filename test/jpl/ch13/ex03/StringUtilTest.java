package jpl.ch13.ex03;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class StringUtilTest {
	@Test
	public void testStringUtil() {
		new StringUtil();
	}

	@Test
	public void testCount() {
		assertThat(StringUtil.delimitedString("<A><B>_<C", '<', '>'), arrayContaining("<A>", "<B>", "<C"));
		assertThat(StringUtil.delimitedString("A><B><C", '<', '>'), arrayContaining("A>", "<B>", "<C"));
		assertThat(StringUtil.delimitedString("<>", '<', '>'), arrayContaining("<>"));
	}
}
