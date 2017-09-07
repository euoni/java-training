package java8.ch05.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;

import org.junit.Test;

public class AircraftTest {
	@Test
	public void testMove() {
		final Aircraft aircraft = new Aircraft(ZonedDateTime.of(LocalDateTime.of(2000, 1, 2, 14, 5), ZoneId.of("CET")));

		final TemporalAmount duration = aircraft
				.move(ZonedDateTime.of(LocalDateTime.of(2000, 1, 2, 16, 40), ZoneId.of("America/Los_Angeles")));

		assertThat(duration, is(Duration.ofMinutes(11 * 60 + 35)));
	}
}
