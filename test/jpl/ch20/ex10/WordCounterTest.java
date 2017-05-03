package jpl.ch20.ex10;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class WordCounterTest {
	protected File file;

	@Before
	public void setUpFile() throws IOException {
		file = File.createTempFile("tmp", null);
		file.deleteOnExit();
		final FileWriter writer = new FileWriter(file);
		writer.write(
				"5 million students study Java. 15 billion devices run Java. 10 million Java developers worldwide. #1 platform for development in the cloud.");
		writer.close();
	}

	@Test
	public void testPrintCount() throws IOException {
		final WordCounter counter = new WordCounter(file);
		counter.count();

		final StdoutCapture sc = new StdoutCapture();
		sc.start();
		counter.printCount();
		sc.stop();
		sc.assertEquals("billion: 1", "cloud: 1", "developers: 1", "development: 1", "devices: 1", "for: 1", "in: 1",
				"java: 3", "million: 2", "platform: 1", "run: 1", "students: 1", "study: 1", "the: 1", "worldwide: 1");
	}
}
