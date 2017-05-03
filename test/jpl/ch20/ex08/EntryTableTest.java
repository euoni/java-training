package jpl.ch20.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.RandomAccessFile;

import org.junit.Before;
import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class EntryTableTest {
	protected File file;

	@Before
	public void setUpFile() throws IOException {
		file = File.createTempFile("tmp", null);
		file.deleteOnExit();
		final FileWriter writer = new FileWriter(file);
		writer.write("header\n");
		writer.write("%%Entry 1\n");
		writer.write("Content 1\n");
		writer.write("%%Entry 2\n");
		writer.write("Content 2\n");
		writer.write("%%Entry 3\n");
		writer.write("Content 3\n");
		writer.close();
	}

	@Test
	public void testPrintEntry() throws IOException {
		final EntryTable table = new EntryTable(new RandomAccessFile(file, "r"));
		table.readTable();
		final StdoutCapture sc = new StdoutCapture();

		// Entry 1
		sc.start();
		table.printEntry(0);
		sc.stop();
		sc.assertEquals("Entry 1\nContent 1");

		// Entry 2
		sc.start();
		table.printEntry(1);
		sc.stop();
		sc.assertEquals("Entry 2\nContent 2");

		// Entry 3
		sc.start();
		table.printEntry(2);
		sc.stop();
		sc.assertEquals("Entry 3\nContent 3");
	}

	@Test
	public void testPrintRandomEntry() throws IOException {
		final EntryTable table = new EntryTable(new RandomAccessFile(file, "r"));
		table.readTable();

		// Capture
		final PrintStream out = System.out;
		final ByteArrayOutputStream buf = new ByteArrayOutputStream();
		System.setOut(new PrintStream(buf));

		try {
			table.printRandomEntry();
		} finally {
			System.out.flush();
			System.setOut(out);
		}

		assertThat(buf.toString().replaceAll("(\r?\n)*$", ""),
				anyOf(is("Entry 1\nContent 1"), is("Entry 2\nContent 2"), is("Entry 3\nContent 3")));
	}

}
