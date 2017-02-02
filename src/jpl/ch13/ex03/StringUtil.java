package jpl.ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class StringUtil {
	public static String[] delimitedString(String from, char start, char end) {
		final List<String> buf = new ArrayList<>();

		int ptr = 0;
		while (true) {
			final int startPos = from.indexOf(start, ptr);
			final int endPos = from.indexOf(end, ptr);
			if (startPos == -1) {
				break;
			} else if (endPos == -1) {
				buf.add(from.substring(startPos));
				break;
			} else if (startPos > endPos) {
				buf.add(from.substring(ptr, endPos + 1));
				ptr = startPos;
			} else {
				buf.add(from.substring(startPos, endPos + 1));
				ptr = endPos + 1;
			}
		}

		final String[] result = new String[buf.size()];
		return buf.toArray(result);
	}
}
