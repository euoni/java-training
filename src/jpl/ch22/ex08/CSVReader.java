package jpl.ch22.ex08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
	static int CELLS = 4;

	public static List<String[]> readCSVTable(Readable source) throws IOException {
		@SuppressWarnings("resource")
		final Scanner in = new Scanner(source);
		final List<String[]> vals = new ArrayList<>();
		final String exp = "^([^,]*),([^,]*),([^,]*),([^,]*)";
		final Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			final String line = in.findInLine(pat);
			if (line != null) {
				final String[] cells = new String[CELLS];
				final MatchResult match = in.match();
				for (int i = 0; i < CELLS; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);

				if (in.findInLine(".") != null)
					throw new IOException("input format error");
				if (in.hasNextLine())
					in.nextLine();
			} else {
				throw new IOException("input format error");
			}
		}

		final IOException ex = in.ioException();
		if (ex != null)
			throw ex;

		return vals;
	}
}
