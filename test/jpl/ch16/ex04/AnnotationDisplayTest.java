package jpl.ch16.ex04;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class AnnotationDisplayTest {
	@Test
	public void testAnnotationDisplay() {
		new AnnotationDisplay();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		AnnotationDisplay.main(new String[] { "jpl.ch16.ex04.Sample" });

		sc.stop();
		sc.assertEquals("interface jpl.ch16.ex04.Sample", "  @jpl.ch16.ex04.Sample()",
				"  @java.lang.annotation.Retention(value=RUNTIME)");
	}

	@Test
	public void testMainError() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		AnnotationDisplay.main(new String[] { "not.existsing.Class" });

		sc.stop();
		sc.assertEquals("");
	}
}
