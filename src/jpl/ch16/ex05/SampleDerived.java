package jpl.ch16.ex05;

import jpl.ch16.ex03.SampleBase;
import jpl.ch16.ex04.Sample;

@Sample
public class SampleDerived extends SampleBase {
	@SuppressWarnings("unused")
	private final int privateDerived = 1;
	public final int publicDerived = 1;

	SampleDerived() {
	}

	void sample() {
	}
}
