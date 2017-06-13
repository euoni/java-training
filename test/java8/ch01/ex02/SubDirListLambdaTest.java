package java8.ch01.ex02;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class SubDirListLambdaTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testCtor() {
		new SubDirListLambda();
	}

	@Test
	public void testMain() throws IOException {
		folder.newFile("file1");
		final File folder1 = folder.newFolder("folder1");
		final File folder2 = folder.newFolder("folder2");

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		SubDirListLambda.main(new String[] { folder.getRoot().getPath() });

		sc.stop();
		sc.assertEquals(folder1.getPath(), folder2.getPath());
	}
}
