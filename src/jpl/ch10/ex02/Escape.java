package jpl.ch10.ex02;

public class Escape {
	public static String escapeString(String raw) {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < raw.length(); i++) {
			final char c = raw.charAt(i);
			switch (c) {
			case '\n':
				result.append("\\\n");
				break;
			case '\t':
				result.append("\\\t");
				break;
			case '\b':
				result.append("\\\b");
				break;
			case '\r':
				result.append("\\\r");
				break;
			case '\f':
				result.append("\\\f");
				break;
			case '\\':
				result.append("\\\\");
				break;
			case '\'':
				result.append("\\\'");
				break;
			case '\"':
				result.append("\\\"");
				break;
			default:
				result.append(c);
				break;
			}
		}
		return result.toString();
	}
}
