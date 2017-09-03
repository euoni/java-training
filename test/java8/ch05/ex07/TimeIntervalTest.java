package java8.ch05.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Test;

public class TimeIntervalTest {
	@Test
	public void testIsOverlapOnSomeDay() {
		final LocalDate date = LocalDate.of(2000, 1, 1);

		assertThat(new TimeInterval(date.atTime(0, 0), date.atTime(1, 0))
				.isOverlap(new TimeInterval(date.atTime(0, 0), date.atTime(1, 0))), is(true));

		assertThat(new TimeInterval(date.atTime(0, 0), date.atTime(1, 0))
				.isOverlap(new TimeInterval(date.atTime(1, 0), date.atTime(2, 0))), is(false));
		assertThat(new TimeInterval(date.atTime(1, 0), date.atTime(2, 0))
				.isOverlap(new TimeInterval(date.atTime(0, 0), date.atTime(1, 0))), is(false));

		assertThat(new TimeInterval(date.atTime(0, 0), date.atTime(1, 0))
				.isOverlap(new TimeInterval(date.atTime(0, 30), date.atTime(1, 30))), is(true));
		assertThat(new TimeInterval(date.atTime(0, 30), date.atTime(1, 30))
				.isOverlap(new TimeInterval(date.atTime(0, 0), date.atTime(1, 0))), is(true));
	}

	@Test
	public void testIsOverlapOnDays() {
		final LocalTime time = LocalTime.of(0, 0);

		assertThat(new TimeInterval(time.atDate(LocalDate.of(2000, 1, 1)), time.atDate(LocalDate.of(2000, 1, 2)))
				.isOverlap(
						new TimeInterval(time.atDate(LocalDate.of(2000, 1, 1)), time.atDate(LocalDate.of(2000, 1, 2)))),
				is(true));

		assertThat(new TimeInterval(time.atDate(LocalDate.of(2000, 1, 1)), time.atDate(LocalDate.of(2000, 1, 2)))
				.isOverlap(
						new TimeInterval(time.atDate(LocalDate.of(2000, 1, 2)), time.atDate(LocalDate.of(2000, 1, 3)))),
				is(false));
		assertThat(new TimeInterval(time.atDate(LocalDate.of(2000, 1, 2)), time.atDate(LocalDate.of(2000, 1, 3)))
				.isOverlap(
						new TimeInterval(time.atDate(LocalDate.of(2000, 1, 1)), time.atDate(LocalDate.of(2000, 1, 2)))),
				is(false));

		assertThat(new TimeInterval(time.atDate(LocalDate.of(2000, 1, 1)), time.atDate(LocalDate.of(2000, 1, 3)))
				.isOverlap(
						new TimeInterval(time.atDate(LocalDate.of(2000, 1, 2)), time.atDate(LocalDate.of(2000, 1, 4)))),
				is(true));
		assertThat(new TimeInterval(time.atDate(LocalDate.of(2000, 1, 2)), time.atDate(LocalDate.of(2000, 1, 4)))
				.isOverlap(
						new TimeInterval(time.atDate(LocalDate.of(2000, 1, 1)), time.atDate(LocalDate.of(2000, 1, 3)))),
				is(true));
	}
}
