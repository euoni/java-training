package jpl.ch01.ex16;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MyUtilitiesTest {
	@Test(expected = BadDataSetException.class)
	public void testGetDataSet() throws BadDataSetException {
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
