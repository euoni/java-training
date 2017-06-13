package java8.ch01.ex04;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileTypeSorterTest {
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	public void testCtor() {
		new FileTypeSorter();
	}

	@Test
	public void testMain() throws IOException {
		final File file1 = folder.newFile("file1");
		final File file2 = folder.newFile("file2");
		final File file3 = folder.newFile("file3");
		final File folder1 = folder.newFolder("folder1");

		final File[] files = { file2, file1, folder1, file3 };

		FileTypeSorter.sort(files);

		assertThat(files, arrayContaining(folder1, file1, file2, file3));
	}
}
