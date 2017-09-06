package java8.ch05.ex11;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;

public class Aircraft {
	private ZonedDateTime time;

	public Aircraft(ZonedDateTime time) {
		this.time = time;
	}

	public TemporalAmount move(ZonedDateTime destination) {
		final Duration duration = Duration.between(time, destination);
		time = destination;
		return duration;
	}
}
