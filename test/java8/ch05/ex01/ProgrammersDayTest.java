package java8.ch05.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class ProgrammersDayTest {
	@Test
	public void testCtor() {
		new ProgrammersDay();
	}

	@Test
	public void testProgrammersDay() {
		assertThat(ProgrammersDay.programmersDay(2000), is(LocalDate.of(2000, 9, 12)));
		assertThat(ProgrammersDay.programmersDay(2001), is(LocalDate.of(2001, 9, 13)));
		assertThat(ProgrammersDay.programmersDay(2002), is(LocalDate.of(2002, 9, 13)));
		assertThat(ProgrammersDay.programmersDay(2003), is(LocalDate.of(2003, 9, 13)));
		assertThat(ProgrammersDay.programmersDay(2004), is(LocalDate.of(2004, 9, 12)));
		assertThat(ProgrammersDay.programmersDay(2100), is(LocalDate.of(2100, 9, 13)));
		assertThat(ProgrammersDay.programmersDay(2400), is(LocalDate.of(2400, 9, 12)));
	}
}
