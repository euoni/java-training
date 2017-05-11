package jpl.ch21.ex06;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ConcatTest {
	@Test
	public void testConcat() {
		new Concat();
	}

	@Test
	public void testMain() throws IOException {
		final File file1 = File.createTempFile("tmp", null);
		file1.deleteOnExit();
		FileWriter writer = new FileWriter(file1);
		writer.write("a");
		writer.close();

		final File file2 = File.createTempFile("tmp", null);
		file2.deleteOnExit();
		writer = new FileWriter(file2);
		writer.write("b");
		writer.close();

		final StdoutCapture sc = new StdoutCapture();
		sc.start();
		Concat.main(new String[] { file1.getPath(), file2.getPath() });
		sc.stop();
		sc.assertEquals("ab");
	}

	@Test(expected = RuntimeException.class)
	public void testMainNotExistingFile() throws IOException {
		Concat.main(new String[] { "not-existing" });
	}
}
