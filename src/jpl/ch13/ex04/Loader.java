package jpl.ch13.ex04;

import java.util.ArrayList;
import java.util.List;

public class Loader {
	public static List<Object> load(String str) {
		final List<Object> result = new ArrayList<>();

		final String[] lines = str.split("\n");
		for (final String line : lines) {
			final String[] tokens = line.split("\\s+", 2);
			final String type = tokens[0].trim();
			final String value = tokens.length == 2 ? tokens[1] : null;

			switch (type) {
			case "String":
				result.add(value);
				break;
			case "Boolean":
				result.add(Boolean.valueOf(value));
				break;
			case "Byte":
				result.add(Byte.parseByte(value));
				break;
			case "Character":
				result.add(value.charAt(0));
				break;
			default:
				throw new UnsupportedOperationException("Type \"" + type + "\" is not supported");
			}
		}

		return result;
	}
}
