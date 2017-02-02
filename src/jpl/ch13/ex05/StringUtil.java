package jpl.ch13.ex05;

public class StringUtil {
	public static String insertComma(String str) {
		final StringBuilder buf = new StringBuilder(str);
		for (int i = buf.length() - 3; i > 0; i -= 3) {
			buf.insert(i, ",");
		}
		return buf.toString();
	}
}
