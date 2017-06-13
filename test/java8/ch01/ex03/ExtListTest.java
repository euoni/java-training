package java8.ch01.ex03;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class ExtListTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	public void testCtor() {
		new ExtList();
	}

	@Test
	public void testMain() throws IOException {
		final File txt = folder.newFile("file.txt");
		folder.newFile("file.png");
		folder.newFolder("folder");

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		ExtList.main(new String[] { folder.getRoot().getPath(), ".txt" });

		sc.stop();
		sc.assertEquals(txt.getName());
	}
}
