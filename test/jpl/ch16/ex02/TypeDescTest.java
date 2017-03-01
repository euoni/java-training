package jpl.ch16.ex02;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class TypeDescTest {
	@Test
	public void testMainObject() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		TypeDesc.main(new String[] { "java.lang.Object" });

		sc.stop();
		sc.assertEquals("class java.lang.Object");
	}

	@Test
	public void testMainHashMap() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		TypeDesc.main(new String[] { "java.util.HashMap" });

		sc.stop();
		sc.assertEquals("class java.util.HashMap<K, V, \b\b>", " implements java.util.Map<K, V, \b\b>",
				" implements java.lang.Cloneable", " implements java.io.Serializable",
				" extends java.util.AbstractMap<K, V, \b\b>", "  implements java.util.Map<K, V, \b\b>");
	}

	@Test
	public void testMainMapEntry() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		TypeDesc.main(new String[] { "java.util.Map$Entry" });

		sc.stop();
		sc.assertEquals("interface java.util.Map.Entry<K, V, \b\b> in java.util.Map.Entry");
	}

	@Test
	public void testMainError() {
		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		TypeDesc.main(new String[] { "not.existsing.Class" });

		sc.stop();
		sc.assertEquals("");
	}
}
