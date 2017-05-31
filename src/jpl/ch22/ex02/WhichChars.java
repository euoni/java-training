package jpl.ch22.ex02;

import java.util.HashSet;
import java.util.TreeSet;

public class WhichChars {
	private final HashSet<Character> used = new HashSet<>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			used.add(str.charAt(i));
		}
	}

	@Override
	public String toString() {
		String desc = "[";
		for (final Character c : new TreeSet<>(used)) {
			desc += c;
		}
		return desc + "]";
	}
}
