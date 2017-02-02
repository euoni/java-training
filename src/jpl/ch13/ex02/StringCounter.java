package jpl.ch13.ex02;

public class StringCounter {
	public static int count(String str, String pattern) {
		int count = 0;
		int ptr = 0;
		while (true) {
			final int index = str.indexOf(pattern, ptr);
			if (index == -1)
				break;
			count++;
			ptr = index + pattern.length();
		}
		return count;
	}
}
