package java8.ch05.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DayCountTest {
	@Test
	public void testCtor() {
		new DayCount();
	}

	@Test
	public void testCount() {
		final LocalDate now = LocalDate.now();

		assertThat(DayCount.count(now), is(0L));

		assertThat(DayCount.count(now.minusDays(1)), is(1L));
		assertThat(DayCount.count(now.minusDays(100)), is(100L));

		assertThat(DayCount.count(now.plusDays(1)), is(-1L));
		assertThat(DayCount.count(now.plusDays(100)), is(-100L));
	}
}
