package jpl.ch09.ex01;

public class Infinity {
	public static void main(String[] args) {
		final double PINF = Double.POSITIVE_INFINITY;
		final double NINF = Double.NEGATIVE_INFINITY;

		System.out.printf("(+∞) + (+∞) = %f%n", PINF + PINF);
		System.out.printf("(+∞) + (-∞) = %f%n", PINF + NINF);
		System.out.printf("(+∞) - (+∞) = %f%n", PINF - PINF);
		System.out.printf("(+∞) - (-∞) = %f%n", PINF - NINF);
		System.out.printf("(+∞) * (+∞) = %f%n", PINF * PINF);
		System.out.printf("(+∞) * (-∞) = %f%n", PINF * NINF);
		System.out.printf("(+∞) / (+∞) = %f%n", PINF / PINF);
		System.out.printf("(+∞) / (-∞) = %f%n", PINF / NINF);
	}
}
