package java8.ch05.ex08;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class Offsets {
	static Clock clock = Clock.systemDefaultZone();

	public static Map<ZoneId, ZoneOffset> withAllTimezone(LocalDateTime time) {
		return ZoneId.getAvailableZoneIds().stream().map(ZoneId::of).map(z -> ZonedDateTime.of(time, z))
				.collect(Collectors.toMap(ZonedDateTime::getZone, ZonedDateTime::getOffset));
	}

	public static void main(String[] args) {
		withAllTimezone(LocalDateTime.now(clock)).entrySet().stream()
				.sorted((e1, e2) -> e1.getKey().getId().compareTo(e2.getKey().getId()))
				.forEach(e -> System.out.printf("%s: %s%n", e.getKey(), e.getValue()));
		;
	}
}
