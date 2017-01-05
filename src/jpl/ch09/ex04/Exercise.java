package jpl.ch09.ex04;

public class Exercise {
	private static void printExpression(String exp, Object actual, String ans) {
		String ret = String.format("%s = %s [actual (%s)%s]", exp, ans, actual.getClass().getSimpleName(), actual);
		ret = ret.replace("Integer", "int").replace("Double", "double").replace("Boolean", "boolean");
		System.out.println(ret);
	}

	public static void main(String[] args) {
		printExpression("3 << 2L - 1", 3 << 2L - 1, "(int)6");
		printExpression("(3 << 2L) - 1", (3 << 2L) - 1, "(long)11");
		printExpression("10 < 12 == 6 > 17", 10 < 12 == 6 > 17, "(bool)false");
		printExpression("10 << 12 == 6 >> 17", 10 << 12 == 6 >> 17, "(bool)false");
		printExpression("13.5e-1 % Float.POSITIVE_INFINITY", 13.5e-1 % Float.POSITIVE_INFINITY, "(double)1.35");
		printExpression("Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY",
				Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY, "(double)NaN");
		printExpression("Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY",
				Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY, "(double)Infinity");
		printExpression("0.0 / -0.0 == -0.0 / 0.0", 0.0 / -0.0 == -0.0 / 0.0, "(boolean)false");
		printExpression("Integer.MAX_VALUE + Integer.MIN_VALUE", Integer.MAX_VALUE + Integer.MIN_VALUE, "(int)-1");
		printExpression("Long.MAX_VALUE + 5", Long.MAX_VALUE + 5, "(long)-9223372036854775804");
		printExpression("(short) 5 * (byte) 10", (short) 5 * (byte) 10, "(int)50");
	}
}
