package jpl.ch22.ex10;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

public class CommentScannerTest {
	@Test
	public void testCommentScanner() {
		new CommentScanner();
	}

	@Test
	public void testScan1() {
		final StringReader source = new StringReader("# This is a comment line\ntoken\ntoken2");
		final List<String> list = CommentScanner.scan(source);

		assertThat(list, contains("token", "token2"));
	}

	@Test
	public void testScan2() {
		final StringReader source = new StringReader("token\n# This is a comment line\ntoken2");
		final List<String> list = CommentScanner.scan(source);

		for (final String string : list) {
			System.out.println(string);
		}

		assertThat(list, contains("token", "token2"));
	}

	@Test
	public void testScan3() {
		final StringReader source = new StringReader("token\ntoken2\n# This is a comment line");
		final List<String> list = CommentScanner.scan(source);

		for (final String string : list) {
			System.out.println(string);
		}

		assertThat(list, contains("token", "token2"));
	}
}
