package jpl.ch01.ex04;

public class SquareTable {
	/** 11～21を二乗した数を出力する */
	public static void main(String[] args) {
		for (int i = 11; i < 21; i++) {
			final int sq = i * i;
			System.out.printf("%d * %<d = %d%n", i, sq);
		}
	}
}
