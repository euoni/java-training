package jpl.ch03.ex11;

public class EvilSimpleSafeSortDouble1 extends SafeSortDouble {
	@Override
	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if (compare(i, j) > 0)
					swap(i, j);
			}
		}

		// call SortMetrics.init()
		if (getDataLength() > 0)
			sort(new double[0]);
	}
}
