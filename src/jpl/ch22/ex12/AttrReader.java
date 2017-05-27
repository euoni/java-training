package jpl.ch22.ex12;

import java.io.Reader;
import java.util.Scanner;

import jpl.ch05.ex01.Attributed;

public class AttrReader {
	public static Attributed readAttrs(Reader source) {
		final AttributedImpl attrs = new AttributedImpl();
		Attributed.Attr attr = null;

		@SuppressWarnings("resource")
		final Scanner in = new Scanner(source);
		in.useDelimiter("=|\r?\n");
		while (in.hasNext()) {
			if (attr == null) {
				attr = new Attributed.Attr(in.next());
			} else {
				attr.setValue(in.next());
				attrs.add(attr);
				attr = null;
			}
		}

		return attrs;
	}
}
