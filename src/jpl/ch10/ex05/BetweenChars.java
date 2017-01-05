package jpl.ch10.ex05;

public class BetweenChars {
	public static void print(char c1, char c2) {
		if (c1 > c2) {
			final char t = c1;
			c1 = c2;
			c2 = t;
		}
		for (char c = c1; c <= c2; c++) {
			System.out.print(c);
		}
		System.out.println();
	}

	public static void main(String[] args) {
		print('a', 'z');
	}
}
