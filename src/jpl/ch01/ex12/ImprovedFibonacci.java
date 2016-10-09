package jpl.ch01.ex12;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'を付けて，フィボナッチ数列の
	 * 最初の方の要素を表示する
	 */
	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		final String[] result = new String[MAX_INDEX];

		result[0] = "1: " + lo;
		for (int i = 2; i <= MAX_INDEX; ++i) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			result[i - 1] = i + ": " + hi + mark;
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int i = 0; i < MAX_INDEX; ++i) {
			System.out.println(result[i]);
		}
	}
}
