package jpl.ch22.ex07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class CSVReader {
	public static List<String[]> readCSVTable(Readable source, int cells) throws IOException {
		@SuppressWarnings("resource")
		final Scanner in = new Scanner(source);
		final List<String[]> vals = new ArrayList<>();
		final String exp = "^([^,]*)(,[^,]*){" + (cells - 1) + "}";
		final Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			final String line = in.findInLine(pat);
			if (line != null) {
				final MatchResult match = in.match();
				vals.add(match.group(0).split(","));
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
