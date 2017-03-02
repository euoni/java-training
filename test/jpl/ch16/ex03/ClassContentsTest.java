package jpl.ch16.ex03;

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

		ClassContents.main(new String[] { "jpl.ch16.ex03.SampleDerived" });

		sc.stop();
		sc.assertEquals("class jpl.ch16.ex03.SampleDerived", "  public final int jpl.ch16.ex03.SampleBase.publicBase",
				"  private final int jpl.ch16.ex03.SampleDerived.privateDerived", "  jpl.ch16.ex03.SampleDerived()",
				"  void jpl.ch16.ex03.SampleDerived.sample()");
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
