package jpl.ch22.ex09;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

public class CSVReader {
	static String[] PATTERNS = new String[] { "^([^,]*),([^,]*),([^,]*),([^,]*)",
			"^(.*?),(.*?),(.*?),(.*?)(?=\\r\\n|\\n|,|$)", "(.*?),(.*?),(.*?),([^,]*)",
			"^(.*?),(.*?),(.*?),(.*?)((?![^,])|$)" };
	static int CELLS = 4;

	private final Pattern pat;

	public CSVReader(String exp) {
		pat = Pattern.compile(exp, Pattern.MULTILINE);
	}

	public List<String[]> readCSVTable(Readable source) throws IOException {
		@SuppressWarnings("resource")
		final Scanner in = new Scanner(source);
		final List<String[]> vals = new ArrayList<>();
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

	public static void benchSingle(CSVReader reader, String string) throws IOException {
		final int count = 100;
		final double[] time = new double[count];
		for (int i = 0; i < count; i++) {
			final StringReader buf = new StringReader(string);
			final long startTime = System.nanoTime();
			reader.readCSVTable(buf);
			final long endTime = System.nanoTime();
			time[i] = (endTime - startTime) * 1e-6;
		}
		System.out.printf("%.3fms%n", DoubleStream.of(time).average().getAsDouble());
	}

	public static void bench(String exp) throws IOException {
		System.out.println("exp = " + exp);
		final CSVReader reader = new CSVReader(exp);

		System.out.print("long: ");
		benchSingle(reader,
				"0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000,1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111,2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222,3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333");
		System.out.print("short: ");
		benchSingle(reader, "0,1,2,3");

		System.out.println();
	}

	public static void main(String[] args) throws IOException {
		for (final String exp : PATTERNS) {
			bench(exp);
		}
	}
}
