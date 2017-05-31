package jpl.ch21.ex01;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import jpl.ch20.ex04.LineReader;

public class SortedLineTest {
	@Test
	public void testReadToList() throws IOException {
		final SortedLine sortedLine = new SortedLine(new LineReader(new StringReader("c\nb\na")));
		final List<String> list = sortedLine.readToList();

		assertThat(list, is(contains("a", "b\n", "c\n")));
	}
}
