package jpl.ch20.ex08;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class EntryTable {
	private final RandomAccessFile file;
	private final List<Integer> table;

	public EntryTable(RandomAccessFile file) {
		this.file = file;
		table = new ArrayList<>();
	}

	public void readTable() throws IOException {
		int count = 0;
		while (true) {
			try {
				count = (file.readByte() == '%') ? count + 1 : 0;
				if (count == 2) {
					table.add((int) file.getFilePointer());
				}
			} catch (final EOFException e) {
				break;
			}
		}
	}

	public void printEntry(int index) throws IOException {
		final int first = table.get(index);
		final int end = (index < table.size() - 1) ? table.get(index + 1) - 2 : (int) file.length();

		final int len = end - first;
		final byte[] buf = new byte[len];
		file.seek(first);
		file.read(buf, 0, len);
		System.out.println(new String(buf));
	}

	public void printRandomEntry() throws IOException {
		final int index = (int) (Math.random() * (table.size() - 1));
		printEntry(index);
	}
}
