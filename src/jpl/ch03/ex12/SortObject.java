package jpl.ch03.ex12;

import jpl.ch03.ex11.SortMetrics;

public abstract class SortObject {
	private Object[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	public final SortMetrics sort(Object[] data) {
		values = data;
		curMetrics.init();
		doSort();
		return getMetrics();
	}

	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}

	protected final int getDataLength() {
		return values.length;
	}

	protected final String probe(int i) {
		curMetrics.probeCnt++;
		return values[i].toString();
	}

	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		final String s1 = values[i].toString();
		final String s2 = values[j].toString();
		return s1.compareTo(s2);
	}

	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		final Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	protected abstract void doSort();
}
