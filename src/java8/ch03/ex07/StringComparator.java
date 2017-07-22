package java8.ch03.ex07;

import java.util.Comparator;

public class StringComparator {
	public static Comparator<String> get(boolean reverse, boolean ignoreCase, boolean ignoreSpace) {
		return (s1, s2) -> {
			if (ignoreCase) {
				s1 = s1.toLowerCase();
				s2 = s2.toLowerCase();
			}
			if (ignoreSpace) {
				s1 = s1.replaceAll("\\s", "");
				s2 = s2.replaceAll("\\s", "");
			}
			return s1.compareTo(s2) * (reverse ? -1 : 1);
		};
	}
}
