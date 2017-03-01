package jpl.ch16.ex09;

import java.io.IOException;

import org.junit.Test;

public class SampleDerivedTest {
	@Test
	public void testIterator() {
		new SampleDerived<>(null).iterator();
	}

	@Test
	public void testSampleDouble() throws IOException, Exception {
		new SampleDerived<>(null).sample(0.);
	}

	@Test
	public void testSampleMapOfQStringIntArray() {
		new SampleDerived<>(null).sample(null);
	}
}
