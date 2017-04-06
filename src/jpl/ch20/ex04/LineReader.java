package jpl.ch20.ex04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LineReader extends FilterReader {
	public LineReader(Reader in) {
		super(in);
	}

	public String readLine() throws IOException {
		final StringBuilder line = new StringBuilder();
		int c;
		while ((c = read()) != -1) {
			line.append((char) c);
			if (c == '\n')
				break;
		}
		return line.toString();
	}
}
