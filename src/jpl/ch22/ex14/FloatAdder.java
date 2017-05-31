package jpl.ch22.ex14;

import java.util.StringTokenizer;

public class FloatAdder {
	public static double sum(String str) {
		double sum = 0;

		final StringTokenizer tokens = new StringTokenizer(str);
		while (tokens.hasMoreTokens())
			sum += Double.parseDouble(tokens.nextToken());

		return sum;
	}
}
