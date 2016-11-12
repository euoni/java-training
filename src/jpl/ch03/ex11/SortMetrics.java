package jpl.ch03.ex11;

public final class SortMetrics implements Cloneable {
	public int probeCnt, compareCnt, swapCnt;

	public void init() {
		probeCnt = swapCnt = compareCnt = 0;
	}

	@Override
	public String toString() {
		return probeCnt + " probes " + compareCnt + " comapres " + swapCnt + " swaps";
	}

	@Override
	public SortMetrics clone() {
		try {
			return (SortMetrics) super.clone();
		} catch (final CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}
}
