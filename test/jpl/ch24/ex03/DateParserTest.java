package jpl.ch24.ex03;

import java.util.Locale;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

@RunWith(Enclosed.class)
public class DateParserTest {

	@RunWith(Parameterized.class)
	public static class NormalTest extends FixLocale {
		@Parameters(name = "{0}")
		public static Object[] data() {
			return new Object[] { "1/1/99", "Jan 1, 1999", "January 1, 1999", "Friday, January 1, 1999",
					"Fri, January 1, 1999" };
		}

		@Parameter(0)
		public String str;

		@Test
		public void testParse() {
			final StdoutCapture sc = new StdoutCapture();
			sc.start();

			DateParser.parse(str);

			sc.stop();
			sc.assertEquals("FULL:	Friday, January 1, 1999", "LONG:	January 1, 1999", "MEDIUM:	Jan 1, 1999",
					"SHORT:	1/1/99");
		}
	}

	@RunWith(Parameterized.class)
	public static class ErrorTest extends FixLocale {
		@Parameters(name = "{0}")
		public static Object[] data() {
			return new Object[] { "1-1-99", "1_1_99", "1,1,99", "1,1,99", "1/1", "1 Jan 1999", "Fri January 1 1999" };
		}

		@Parameter(0)
		public String str;

		@Test(expected = IllegalArgumentException.class)
		public void testParse() {
			DateParser.parse(str);
		}
	}

	public static class CtorTest extends FixLocale {
		@Test
		public void test() {
			new DateParser();
		}
	}
}

class FixLocale {
	private Locale defaultLocale;

	@Before
	public void setUp() throws Exception {
		defaultLocale = Locale.getDefault();
		Locale.setDefault(Locale.ENGLISH);
	}

	@After
	public void tearDown() throws Exception {
		Locale.setDefault(defaultLocale);
	}
}