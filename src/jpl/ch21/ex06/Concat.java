package jpl.ch21.ex06;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.NoSuchElementException;

public class Concat {
	public static void main(String[] args) throws IOException {
		InputStream in;
		if (args.length == 0) {
			in = System.in;
		} else {
			final Enumeration<InputStream> files = new FileList(args);
			in = new SequenceInputStream(files);
		}
		int ch;
		while ((ch = in.read()) != -1)
			System.out.write(ch);
	}

	private static class FileList implements Enumeration<InputStream> {
		private final String[] files;
		private int index;
		private InputStream stream;

		public FileList(String[] files) {
			this.files = files;
			this.index = 0;
		}

		@Override
		public boolean hasMoreElements() {
			return index < files.length;
		}

		@Override
		public InputStream nextElement() {
			if (!hasMoreElements())
				throw new NoSuchElementException();

			// close last stream
			try {
				if (stream != null)
					stream.close();
			} catch (final IOException e) {
				throw new RuntimeException("close failed");
			}

			// open
			try {
				stream = new BufferedInputStream(new FileInputStream(files[index++]));
			} catch (final FileNotFoundException e) {
				throw new RuntimeException("file not found");
			}

			return stream;
		}

	}
}
