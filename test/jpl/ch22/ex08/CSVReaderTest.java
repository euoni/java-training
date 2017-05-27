package jpl.ch22.ex08;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class CSVReaderTest {
	@Test
	public void testCSVReader() {
		new CSVReader();
	}

	@Test(expected = IOException.class)
	public void testReadCSVTableFormatError1() throws IOException {
		final StringReader buf = new StringReader("0\n");
		CSVReader.readCSVTable(buf);
	}

	@Test(expected = IOException.class)
	public void testReadCSVTableFormatError2() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3,4\n");
		CSVReader.readCSVTable(buf);
	}

	@Test(expected = IOException.class)
	public void testReadCSVTablePassthrough() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3\n");
		buf.close();
		CSVReader.readCSVTable(buf);
	}

	@Test
	public void testReadCSVTableNoLastEmptyLine() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3");
		final List<String[]> table = CSVReader.readCSVTable(buf);

		assertThat(table.size(), is(1));
		assertThat(table.get(0), arrayContaining("0", "1", "2", "3"));
	}

	@Test
	public void testReadCSVTableLastEmptyLine() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3\n");
		final List<String[]> table = CSVReader.readCSVTable(buf);

		assertThat(table.size(), is(1));
		assertThat(table.get(0), arrayContaining("0", "1", "2", "3"));
	}
}
