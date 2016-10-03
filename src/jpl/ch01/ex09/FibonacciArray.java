package jpl.ch01.ex09;

public class FibonacciArray {
	/** 値が50未満のフィボナッチ数列を表示する */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;

		final int[] result = new int[9];
		int i = 0;
		result[i] = lo;

		while (hi < 50) {
			i++;
			result[i] = hi;
			hi = lo + hi; // 新しいhi
			lo = hi - lo; // 新しいloは，（合計 - 古いlo）すなわち，古いhi
		}

		for (i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
}
