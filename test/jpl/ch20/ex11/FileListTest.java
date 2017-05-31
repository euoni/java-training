package jpl.ch20.ex11;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class FileListTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testPrint() throws IOException {
		folder.newFile("1.x");
		folder.newFile("1.y");
		final File z1 = folder.newFile("1.z");
		final File z2 = folder.newFile("2.z");
		folder.newFolder();

		final StdoutCapture sc = new StdoutCapture();
		sc.start();
		final FileList list = new FileList(".z");
		list.print(folder.getRoot());
		sc.stop();
		sc.assertEquals(z1.toString(), z2.toString());
	}
}
