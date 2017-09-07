package java8.ch05.ex12;

import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

public class Alarm {
	Clock clock = Clock.systemDefaultZone();
	private final Map<String, ZonedDateTime> list = new HashMap<>();

	public void add(String title, ZonedDateTime time) {
		list.put(title, time);
	}

	public void print() {
		list.entrySet().stream().sorted((e1, e2) -> e1.getValue().compareTo(e2.getValue())).forEach(e -> {
			final String mark = e.getValue().isBefore(ZonedDateTime.now(clock).plusHours(1)) ? "*" : " ";
			System.out.printf("%s %s at %s%n", mark, e.getKey(), e.getValue().withZoneSameInstant(clock.getZone()));
		});
	}
}
