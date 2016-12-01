package jpl.ch06.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class WeekTest {
	@Test
	public void testValues() {
		assertThat(Week.values(), is(new Week[] { Week.MONDAY, Week.TUESDAY, Week.WEDNESDAY, Week.THURSDAY, Week.FRIDAY,
				Week.SATURDAY, Week.SUNDAY }));
	}

	@Test
	public void testValueOf() {
		assertThat(Week.valueOf("MONDAY"), theInstance(Week.MONDAY));
		assertThat(Week.valueOf("TUESDAY"), theInstance(Week.TUESDAY));
		assertThat(Week.valueOf("WEDNESDAY"), theInstance(Week.WEDNESDAY));
		assertThat(Week.valueOf("THURSDAY"), theInstance(Week.THURSDAY));
		assertThat(Week.valueOf("FRIDAY"), theInstance(Week.FRIDAY));
		assertThat(Week.valueOf("SATURDAY"), theInstance(Week.SATURDAY));
		assertThat(Week.valueOf("SUNDAY"), theInstance(Week.SUNDAY));
	}
}
