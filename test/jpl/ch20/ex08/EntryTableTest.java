package jpl.ch20.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.BufferedWriter;
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
		final BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write("header");
		writer.newLine();
		writer.write("%%Entry 1");
		writer.newLine();
		writer.write("Content 1");
		writer.newLine();
		writer.write("%%Entry 2");
		writer.newLine();
		writer.write("Content 2");
		writer.newLine();
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
		sc.assertEquals("Entry 1", "Content 1");

		// Entry 2
		sc.start();
		table.printEntry(1);
		sc.stop();
		sc.assertEquals("Entry 2", "Content 2");
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
				anyOf(is("Entry 1" + System.lineSeparator() + "Content 1"),
						is("Entry 2" + System.lineSeparator() + "Content 2")));
	}

}
