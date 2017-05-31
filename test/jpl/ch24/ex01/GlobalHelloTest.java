package jpl.ch24.ex01;

import java.util.Arrays;
import java.util.Collection;
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
public class GlobalHelloTest {
	@RunWith(Parameterized.class)
	public static class LocaleTest {
		private Locale defaultLocale;

		@Parameters(name = "{0}: {1}, {2}")
		public static Collection<Object[]> data() {
			return Arrays.asList(new Object[][] { { Locale.JAPAN, "Ciao", "Ciao" },
					{ Locale.ENGLISH, "Hello", "Goodbye" }, { new Locale("en", "AU"), "G'day", "Goodbye" },
					{ Locale.GERMAN, "Hallo", "Tschüs" }, { Locale.FRENCH, "Bonjour", "Au Revoir" } });
		}

		@Parameter(0)
		public Locale locale;

		@Parameter(1)
		public String helloMsg;

		@Parameter(2)
		public String goodbyMsg;

		@Before
		public void setUp() throws Exception {
			defaultLocale = Locale.getDefault();
			Locale.setDefault(locale);
		}

		@After
		public void tearDown() throws Exception {
			Locale.setDefault(defaultLocale);
		}

		@Test
		public void testHello() {
			final StdoutCapture sc = new StdoutCapture();
			sc.start();

			GlobalHello.main(new String[] {});

			sc.stop();
			sc.assertEquals(helloMsg);
		}

		@Test
		public void testGoodbye() {
			final StdoutCapture sc = new StdoutCapture();
			sc.start();

			GlobalHello.main(new String[] { "arg" });

			sc.stop();
			sc.assertEquals(goodbyMsg);
		}
	}

	public static class CtorTest {
		@Test
		public void test() {
			new GlobalHello();
		}
	}
}
