package jpl.ch03.ex11;

public class EvilSimpleSafeSortDouble2 extends SafeSortDouble {
	@Override
	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0)
					swap(i, j);
			}
		}

		// overflow counters
		final long iter = 4294967296L;
		for (long i = getMetrics().compareCnt; i < iter; i++)
			compare(0, 0);
		for (long i = getMetrics().swapCnt; i < iter; i++)
			swap(0, 0);
	}
}
