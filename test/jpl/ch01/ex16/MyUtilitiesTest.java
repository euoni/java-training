package jpl.ch01.ex16;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MyUtilitiesTest {

	@Test(expected = BadDataSetException.class)
	public void testGetDataSet() throws BadDataSetException {
		final String setName = "not-exists";
		final MyUtilities util = new MyUtilities();
		try {
			util.getDataSet(setName);
		} catch (final BadDataSetException e) {
			assertEquals("not-exists.dset", e.file);
			assertNotNull(e.innerException);
			throw e;
		}
	}

}
