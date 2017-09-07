package java8.ch05.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class AircraftTest {
	@Test
	public void testMove() {
		final Aircraft aircraft = new Aircraft(
				ZonedDateTime.of(LocalDateTime.of(2000, 1, 1, 15, 5), ZoneId.of("America/Los_Angeles")));
		aircraft.move(Duration.ofMinutes(10 * 60 + 50), ZoneId.of("CET"));

		assertThat(aircraft.getTime(), is(ZonedDateTime.of(LocalDateTime.of(2000, 1, 2, 10, 55), ZoneId.of("CET"))));
	}
}
