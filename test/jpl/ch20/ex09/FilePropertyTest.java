package jpl.ch20.ex09;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FilePropertyTest {
	@Test
	public void testPrintPropert() throws IOException {
		final File file = File.createTempFile("tmp", null);
		file.deleteOnExit();

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		final FileProperty property = new FileProperty(file);
		property.printProperty();

		sc.stop();
		sc.assertEquals(file.toString(), "\tgetName(): " + file.getName(), "\tgetPath(): " + file.getPath(),
				"\tgetAbsolutePath(): " + file.getAbsolutePath(), "\tgetCanonicalPath(): " + file.getCanonicalPath(),
				"\tgetParent(): " + file.getParent(), "\texists(): true", "\tcanRead(): true", "\tcanWrite(): true",
				"\tisFile(): true", "\tisDirectory(): false", "\tisAbsolute(): true", "\tisHidden(): false",
				"\tlastModified(): " + file.lastModified(), "\tlength(): 0");
	}
}
