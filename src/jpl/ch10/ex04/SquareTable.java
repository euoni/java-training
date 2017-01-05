package jpl.ch10.ex04;

public class SquareTable {
	/** 11～21を二乗した数を出力する */
	public static void main(String[] args) {
		int i = 11;
		// whileに変更
		while (i < 21) {
			final int sq = i * i;
			System.out.printf("%d * %<d = %d%n", i, sq);
			i++;
		}
	}
}
