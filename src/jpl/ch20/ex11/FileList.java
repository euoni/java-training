package jpl.ch20.ex11;

import java.io.File;
import java.io.FileFilter;

public class FileList {
	private final String suffix;

	public FileList(String suffix) {
		this.suffix = suffix;
	}

	public void print(File dir) {
		for (final File file : dir.listFiles(new Filter())) {
			System.out.println(file);
		}
	}

	private class Filter implements FileFilter {
		@Override
		public boolean accept(File pathname) {
			return pathname.isFile() && pathname.getName().endsWith(suffix);
		}
	}
}
