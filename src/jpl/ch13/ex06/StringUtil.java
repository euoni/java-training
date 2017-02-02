package jpl.ch13.ex06;

public class StringUtil {
	public static String insertComma(String str, String sep, int digit) {
		final StringBuilder buf = new StringBuilder(str);
		for (int i = buf.length() - digit; i > 0; i -= digit) {
			buf.insert(i, sep);
		}
		return buf.toString();
	}
}
