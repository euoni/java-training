package jpl.ch22.ex09;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CSVReaderTest {
	@Parameters
	public static String[] data() {
		return CSVReader.PATTERNS;
	}

	@Parameter
	public String exp;

	@Test(expected = IOException.class)
	public void testReadCSVTableFormatError1() throws IOException {
		final StringReader buf = new StringReader("0\n");
		new CSVReader(exp).readCSVTable(buf);
	}

	@Test(expected = IOException.class)
	public void testReadCSVTableFormatError2() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3,4\n");
		new CSVReader(exp).readCSVTable(buf);
	}

	@Test(expected = IOException.class)
	public void testReadCSVTablePassthrough() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3\n");
		buf.close();
		new CSVReader(exp).readCSVTable(buf);
	}

	@Test
	public void testReadCSVTableNoLastEmptyLine() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3");
		final List<String[]> table = new CSVReader(exp).readCSVTable(buf);

		assertThat(table.size(), is(1));
		assertThat(table.get(0), arrayContaining("0", "1", "2", "3"));
	}

	@Test
	public void testReadCSVTableLastEmptyLine() throws IOException {
		final StringReader buf = new StringReader("0,1,2,3\n");
		final List<String[]> table = new CSVReader(exp).readCSVTable(buf);

		assertThat(table.size(), is(1));
		assertThat(table.get(0), arrayContaining("0", "1", "2", "3"));
	}
}
