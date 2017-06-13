package java8.ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class FileTypeSorter {
	public static File[] sort(File[] files) {
		Arrays.sort(files, (a, b) -> {
			if (a.isDirectory() && b.isFile())
				return -1;
			else if (a.isFile() && b.isDirectory())
				return 1;
			else
				return a.compareTo(b);
		});
		return files;
	}
}
