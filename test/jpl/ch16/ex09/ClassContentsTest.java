package jpl.ch16.ex09;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ClassContentsTest {
	@Test
	public void testClassContents() {
		new ClassContents();
	}

	@Test
	public void testMainSampleBase() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ClassContents.main(new String[] { "jpl.ch16.ex03.SampleBase" });

		sc.stop();
		sc.assertEquals("public class jpl.ch16.ex03.SampleBase {", "  private final int privateBase;", "",
				"  public final int publicBase;", "", "  public jpl.ch16.ex03.SampleBase() {}", "", "}");
	}

	@Test
	public void testMainSampleDerived() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ClassContents.main(new String[] { "jpl.ch16.ex09.SampleDerived" });

		sc.stop();
		sc.assertEquals("@jpl.ch16.ex04.Sample()",
				"public class jpl.ch16.ex09.SampleDerived<T extends Number, , > extends jpl.ch16.ex03.SampleBase implements Iterable<String, >,  {",
				"  private final int privateDerived;", "", "  @jpl.ch16.ex04.Sample()",
				"  public final int publicDerived;", "",
				"  protected java.util.List<java.util.List<? super java.util.List<? extends Number, , >, , >, >[][][][][] generic;",
				"", "  @jpl.ch16.ex04.Sample()",
				"  <U extends java.util.List<String, >, , > jpl.ch16.ex09.SampleDerived(java.util.Map<U extends java.util.List<String, >, , T extends Number, , >, ) {}",
				"",
				"  public Number sample(@jpl.ch16.ex04.Sample() @Deprecated() double, ) throws Exception, java.io.IOException,  {}",
				"", "  public java.util.Iterator<String, > iterator() {}", "", "  @jpl.ch16.ex04.Sample()",
				"  transient strictfp <U, > void sample(java.util.Map<?, String, >, int[], ...) {}", "", "}");
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
