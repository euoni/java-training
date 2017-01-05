package jpl.ch10.ex01;

public class Escape {
	public static String escapeString(String raw) {
		final StringBuilder result = new StringBuilder();
		for (int i = 0; i < raw.length(); i++) {
			final char c = raw.charAt(i);
			if (c == '\n')
				result.append("\\\n");
			else if (c == '\t')
				result.append("\\\t");
			else if (c == '\b')
				result.append("\\\b");
			else if (c == '\r')
				result.append("\\\r");
			else if (c == '\f')
				result.append("\\\f");
			else if (c == '\\')
				result.append("\\\\");
			else if (c == '\'')
				result.append("\\\'");
			else if (c == '\"')
				result.append("\\\"");
			else
				result.append(c);
		}
		return result.toString();
	}
}
