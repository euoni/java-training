package java8.ch05.ex07;

import java.time.LocalDateTime;

public class TimeInterval {
	private final LocalDateTime begin;
	private final LocalDateTime end;

	public TimeInterval(LocalDateTime begin, LocalDateTime end) {
		if (begin.isAfter(end))
			throw new IllegalArgumentException();

		this.begin = begin;
		this.end = end;
	}

	public boolean isOverlap(TimeInterval other) {
		return begin.isBefore(other.end) && end.isAfter(other.begin);
	}
}
