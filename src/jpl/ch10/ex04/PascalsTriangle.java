package jpl.ch10.ex04;

import java.util.Collections;

public class PascalsTriangle {
	public static int[][] triangle(int n) {
		final int[][] result = new int[n][];
		for (int i = 0; i < n; i++) {
			result[i] = new int[i + 1];
			for (int j = 0; j < result[i].length; j++) {
				result[i][j] = binomial(i, j);
			}
		}
		return result;
	}

	public static void print(int[][] tri) {
		final int n = tri.length;
		final int w = (int) Math.log10(tri[n - 1][n / 2]) + 2;

		for (int i = 0; i < n; i++) {
			final int s = w * (n - i - 1) / 2;
			System.out.print(String.join("", Collections.nCopies(s, " ")));
			for (int j = 0; j < tri[i].length; j++) {
				System.out.printf("% " + w + "d", tri[i][j]);
			}
			System.out.println("");
		}
	}

	private static int binomial(int n, int k) {
		if (k > n - k)
			k = n - k;

		// whileに変更
		int b = 1, i = 1, m = n;
		while (i <= k) {
			b = b * (m--) / (i++);
		}
		return b;
	}

	public static void main(String[] args) {
		System.out.println("n=12");
		print(triangle(12));

		System.out.println("n=15");
		print(triangle(15));
	}
}
