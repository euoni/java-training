package java8.ch05.ex10;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAmount;

public class Aircraft {
	private ZonedDateTime time;

	public Aircraft(ZonedDateTime time) {
		this.time = time;
	}

	public void move(TemporalAmount amount, ZoneId destination) {
		time = time.plus(amount).withZoneSameInstant(destination);
	}

	public ZonedDateTime getTime() {
		return time;
	}
}
