package jpl.ch22.ex07;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class CSVReaderTest {
	@Test
	public void testCSVReader() {
		new CSVReader();
	}

	@Test(expected = NoSuchElementException.class)
	public void testReadCSVTableNoElementError() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3");
		CSVReader.readCSVTable(buf, 4);
	}

	@Test(expected = IOException.class)
	public void testReadCSVTableFormatError() throws IOException {
		final StringReader buf = new StringReader("0\n");
		CSVReader.readCSVTable(buf, 4);
	}

	@Test(expected = IOException.class)
	public void testReadCSVTablePassthrough() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3\n");
		buf.close();
		CSVReader.readCSVTable(buf, 4);
	}

	@Test
	public void testReadCSVTable4() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3\n4,5,6,7\n");
		final List<String[]> table = CSVReader.readCSVTable(buf, 4);

		assertThat(table.size(), is(2));
		assertThat(table.get(0), arrayContaining("0", "1", "2", "3"));
		assertThat(table.get(1), arrayContaining("4", "5", "6", "7"));
	}

	@Test
	public void testReadCSVTable3() throws IOException {
		final StringReader buf = new StringReader("0,1,2\n3,4,5\n");
		final List<String[]> table = CSVReader.readCSVTable(buf, 4);

		assertThat(table.size(), is(2));
		assertThat(table.get(0), arrayContaining("0", "1", "2"));
		assertThat(table.get(1), arrayContaining("3", "4", "5"));
	}
}
