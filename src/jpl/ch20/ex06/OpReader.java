package jpl.ch20.ex06;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class OpReader {
	private final Map<String, Double> names = new HashMap<>();

	public void read(Reader source) throws IOException {
		final StreamTokenizer in = new StreamTokenizer(source);

		String name = null;
		Integer op = null;
		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.ttype == StreamTokenizer.TT_WORD) {
				name = in.sval;
				if (!names.containsKey(name))
					names.put(name, 0.);
			} else if (in.ttype == '+' || in.ttype == '-' || in.ttype == '=') {
				op = in.ttype;
			} else if (in.ttype == StreamTokenizer.TT_NUMBER) {
				if (name == null || op == null)
					throw new IllegalStateException();
				if (op == '+') {
					names.put(name, names.get(name) + in.nval);
				} else if (op == '-') {
					names.put(name, names.get(name) - in.nval);
				} else if (op == '=') {
					names.put(name, in.nval);
				}

				name = null;
				op = null;
			} else {
				throw new IllegalStateException();
			}
		}
	}

	public Map<String, Double> getMap() {
		return new HashMap<>(names);
	}
}
