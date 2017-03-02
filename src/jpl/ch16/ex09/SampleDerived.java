package jpl.ch16.ex09;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jpl.ch16.ex03.SampleBase;
import jpl.ch16.ex04.Sample;

@Sample
public class SampleDerived<T extends Number> extends SampleBase implements Iterable<String> {
	@SuppressWarnings("unused")
	private final int privateDerived = 1;

	@Sample
	public final int publicDerived = 1;

	protected List<List<? super List<? extends Number>>>[][][][][] generic;

	@Sample
	<U extends List<String>> SampleDerived(Map<U, T> x) {
	}

	@Override
	public Iterator<String> iterator() {
		return null;
	}

	public Number sample(@Sample @Deprecated double x) throws Exception, IOException {
		return 1;
	}

	@Sample
	strictfp <U> void sample(Map<?, String> x, int... y) {
	}
}
