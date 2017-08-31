package java8.ch05.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;

import org.junit.Test;

public class ConditionalTemporalAdjusterTest {
	@Test
	public void testCtor() {
		new ConditionalTemporalAdjuster();
	}

	@Test
	public void testNext1st() {
		final TemporalAdjuster next1st = ConditionalTemporalAdjuster.next(w -> w.getDayOfMonth() == 1);

		assertThat(LocalDate.of(2000, 1, 1).with(next1st), is(LocalDate.of(2000, 2, 1)));
		assertThat(LocalDate.of(2000, 2, 1).with(next1st), is(LocalDate.of(2000, 3, 1)));
	}

	@Test
	public void testNextLeap() {
		final TemporalAdjuster nextLeap = ConditionalTemporalAdjuster.next(w -> w.isLeapYear());

		assertThat(LocalDate.of(2000, 1, 1).with(nextLeap), is(LocalDate.of(2000, 1, 2)));
		assertThat(LocalDate.of(2001, 1, 1).with(nextLeap), is(LocalDate.of(2004, 1, 1)));
	}
}
