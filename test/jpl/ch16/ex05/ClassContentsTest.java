package jpl.ch16.ex05;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ClassContentsTest {
	@Test
	public void testClassContents() {
		new ClassContents();
	}

	@Test
	public void testMain() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ClassContents.main(new String[] { "jpl.ch16.ex05.SampleDerived" });

		sc.stop();
		sc.assertEquals("@jpl.ch16.ex04.Sample()", "class jpl.ch16.ex05.SampleDerived",
				"  public final int jpl.ch16.ex03.SampleBase.publicBase",
				"  private final int jpl.ch16.ex05.SampleDerived.privateDerived", "  jpl.ch16.ex05.SampleDerived()",
				"  void jpl.ch16.ex05.SampleDerived.sample()");
	}

	@Test
	public void testMainError() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ClassContents.main(new String[] { "not.existsing.Class" });

		sc.stop();
		sc.assertEquals("");
	}
}
