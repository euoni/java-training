package jpl.ch20.ex05;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;

public class FindString {
	private final String word;

	public FindString(String word) {
		this.word = word;
	}

	public void find(Reader in) throws IOException {
		final LineNumberReader reader = new LineNumberReader(in);

		String line;
		while ((line = reader.readLine()) != null) {
			if (line.contains(word))
				System.out.println(reader.getLineNumber() + " " + line);
		}
	}
}
