package jpl.ch22.ex10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommentScanner {
	public static List<String> scan(Readable source) {
		@SuppressWarnings("resource")
		final Scanner in = new Scanner(source);
		in.useDelimiter("(\r?\n)?#.*[\n$]?|\r?\n");
		final List<String> result = new ArrayList<>();
		while (in.hasNext()) {
			result.add(in.next());
		}
		return result;
	}
}
