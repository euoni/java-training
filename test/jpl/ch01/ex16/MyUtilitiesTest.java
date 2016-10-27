package jpl.ch01.ex16;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class MyUtilitiesTest {
	@Test
	public void testGetDataSet() throws IOException, BadDataSetException {
		final File tmp = File.createTempFile("dataset", ".dset");
		tmp.deleteOnExit();

		final MyUtilities util = new MyUtilities();
		String file = tmp.getPath();
		file = file.substring(0, file.lastIndexOf('.'));
		assertThat(util.getDataSet(file), nullValue());
	}

	@Test(expected = BadDataSetException.class)
	public void testGetDataSetError() throws BadDataSetException {
		final String setName = "not-exists";
		final MyUtilities util = new MyUtilities();
		try {
			util.getDataSet(setName);
		} catch (final BadDataSetException e) {
			assertThat(e.file, is("not-exists.dset"));
			assertThat(e.innerException, not(nullValue()));
			throw e;
		}
	}
}
