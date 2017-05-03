package jpl.ch20.ex10;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordCounter {
	private final File file;
	private final Map<String, Integer> count;

	public WordCounter(File file) {
		this.file = file;
		this.count = new HashMap<>();
	}

	public void count() throws IOException {
		final StreamTokenizer tokenizer = new StreamTokenizer(new FileReader(file));
		tokenizer.lowerCaseMode(true);

		while (true) {
			final int token = tokenizer.nextToken();
			String key;
			if (token == StreamTokenizer.TT_WORD)
				key = tokenizer.sval.replaceAll("[,\\.]$", "");
			else if (token == StreamTokenizer.TT_EOF)
				break;
			else
				continue;

			count.put(key, count.getOrDefault(key, 0) + 1);
		}
	}

	public void printCount() {
		final List<String> sortedKeys = new ArrayList<>(count.keySet());
		Collections.sort(sortedKeys);
		for (final String key : sortedKeys) {
			System.out.println(key + ": " + count.get(key));
		}
	}
}
