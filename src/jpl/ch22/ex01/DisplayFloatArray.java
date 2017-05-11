package jpl.ch22.ex01;

import java.util.stream.DoubleStream;

public class DisplayFloatArray {
	static int TERM_COL = 80;

	public static void main(String[] args) {
		final double[] array = new double[20];
		for (int i = 0; i < array.length; i++) {
			array[i] = i * 1.23456789;
		}
		System.out.print(format(array, 10));
	}

	public static String format(double[] array, int col) {
		final double max = DoubleStream.of(array).max().getAsDouble();
		final int iw = TERM_COL / col - 1;
		final int fw = iw - (int) Math.log10(max) - 2;

		final StringBuilder s = new StringBuilder();
		final String format = "%" + iw + "." + fw + "f ";
		for (int i = 0; i < array.length; i++) {
			s.append(String.format(format, array[i]));
			if ((i + 1) % col == 0)
				s.append(System.lineSeparator());
		}
		return s.toString();
	}
}
