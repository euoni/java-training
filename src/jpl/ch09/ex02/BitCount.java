package jpl.ch09.ex02;

public class BitCount {
	public static int sift(int i) {
		int count = 0;
		while (i != 0) {
			count += i & 1;
			i >>>= 1;
		}
		return count;
	}

	public static int algo1(int i) {
		i = i - ((i >>> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
		i = (i + (i >>> 4)) & 0x0f0f0f0f;
		i = i + (i >>> 8);
		i = i + (i >>> 16);
		return i & 0x3f;
	}

	public static int algo2(int i) {
		return Integer.toBinaryString(i).replaceAll("0", "").length();
	}

	public static void main(String[] args) {
		int i;

		i = 0xAAAAAAAA;
		System.out.printf("%s: %d%n", Integer.toBinaryString(i), sift(i));

		i = 0xCCCC5555;
		System.out.printf("%s: %d%n", Integer.toBinaryString(i), sift(i));
	}

}
