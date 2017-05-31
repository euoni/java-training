package jpl.ch22.ex13;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

import jpl.ch05.ex01.Attributed;
import jpl.ch22.ex12.AttributedImpl;

public class AttrReader {
	static String DELIM = "(?<=.)=|\r?\n";

	public static Attributed readAttrs(Reader source) throws IOException {
		final AttributedImpl attrs = new AttributedImpl();
		Attributed.Attr attr = null;

		@SuppressWarnings("resource")
		final Scanner in = new Scanner(source);
		in.useDelimiter(DELIM);
		while (in.hasNext()) {
			if (attr == null) {
				final String name = in.next();
				if (name.charAt(0) == '=')
					throw new IOException("bad Attr name");
				attr = new Attributed.Attr(name);
				in.useDelimiter("\r?\n");
			} else {
				final String val = in.next().substring(1);
				attr.setValue(val);
				attrs.add(attr);
				attr = null;
				in.useDelimiter(DELIM);
			}
		}

		return attrs;
	}
}
