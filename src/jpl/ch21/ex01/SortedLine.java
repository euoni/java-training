package jpl.ch21.ex01;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import jpl.ch20.ex04.LineReader;

public class SortedLine {
	private final LineReader reader;

	public SortedLine(LineReader reader) {
		this.reader = reader;
	}

	public List<String> readToList() throws IOException {
		final List<String> list = new LinkedList<>();
		while (true) {
			final String line = reader.readLine();
			if (line.equals(""))
				break;
			list.add(line);
		}
		Collections.sort(list, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return list;
	}
}
