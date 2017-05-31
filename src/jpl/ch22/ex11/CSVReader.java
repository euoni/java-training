package jpl.ch22.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CSVReader {
	static int CELLS = 4;

	public static List<String[]> readCSVTable(Reader source) throws IOException {
		final BufferedReader buf = new BufferedReader(source);
		final List<String[]> vals = new ArrayList<>();
		String line;
		while ((line = buf.readLine()) != null) {
			final StringTokenizer st = new StringTokenizer(line, ",");
			if (st.countTokens() != CELLS)
				throw new IOException("input format error");
			final String[] cells = new String[CELLS];
			for (int i = 0; i < cells.length; i++) {
				cells[i] = st.nextToken();
			}
			vals.add(cells);
		}
		return vals;
	}
}
