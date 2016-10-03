package jpl.ch01.ex10;

public class ImprovedFibonacciArray {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'を付けて，フィボナッチ数列の
	 * 最初の方の要素を表示する
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;

		final NumericItem[] result = new NumericItem[MAX_INDEX];
		result[0] = new NumericItem(lo);

		for (int i = 2; i <= MAX_INDEX; i++) {
			result[i - 1] = new NumericItem(hi);
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int i = 1; i <= MAX_INDEX; i++) {
			System.out.println(i + ": " + result[i - 1].getMarkedNumber());
		}
	}
}
