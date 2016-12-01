package jpl.ch06.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class VerboseTest {
	@Test
	public void testLevelValues() {
		assertThat(Verbose.Level.values(), is(new Verbose.Level[] { Verbose.Level.SILENT, Verbose.Level.TERSE,
				Verbose.Level.NORMAL, Verbose.Level.VERBOSE }));
	}

	@Test
	public void testLevelValueOf() {
		assertThat(Verbose.Level.valueOf("SILENT"), theInstance(Verbose.Level.SILENT));
		assertThat(Verbose.Level.valueOf("TERSE"), theInstance(Verbose.Level.TERSE));
		assertThat(Verbose.Level.valueOf("NORMAL"), theInstance(Verbose.Level.NORMAL));
		assertThat(Verbose.Level.valueOf("VERBOSE"), theInstance(Verbose.Level.VERBOSE));
	}
}
