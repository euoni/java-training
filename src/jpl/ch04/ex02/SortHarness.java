package jpl.ch04.ex02;

import jpl.ch03.ex11.SortMetrics;

public interface SortHarness<T> {
	SortMetrics sort(T[] data);

	SortMetrics getMetrics();
}
