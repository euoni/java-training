package jpl.ch03.ex11;

public class TestSort {
	static double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };

	public static void sort(SortDouble sort) {
		System.out.println("Sort: " + sort.getClass().getName());
		final double[] values = testData.clone();
		final SortMetrics metrics = sort.sort(values);
		System.out.println("\tMetrics: " + metrics);
		for (final double d : values) {
			System.out.println("\t\t" + d);
		}
	}

	public static void main(String[] args) {
		sort(new EvilSimpleSortDouble1());
		sort(new EvilSimpleSortDouble2());
	}
}
