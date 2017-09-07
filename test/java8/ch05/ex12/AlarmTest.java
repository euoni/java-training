package java8.ch05.ex12;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class AlarmTest {
	@Test
	public void testSameZone() {
		final Alarm alarm = new Alarm();
		alarm.clock = Clock.fixed(Instant.parse("2017-09-07T00:00:00Z"), ZoneId.of("Asia/Tokyo"));

		alarm.add("Meeting 1", ZonedDateTime.of(LocalDateTime.of(2017, 9, 7, 9, 0, 0), ZoneId.of("Asia/Tokyo")));
		alarm.add("Meeting 2", ZonedDateTime.of(LocalDateTime.of(2017, 9, 7, 9, 59, 59), ZoneId.of("Asia/Tokyo")));
		alarm.add("Meeting 3", ZonedDateTime.of(LocalDateTime.of(2017, 9, 7, 10, 0, 0), ZoneId.of("Asia/Tokyo")));

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		alarm.print();

		sc.stop();
		sc.assertEquals("* Meeting 1 at 2017-09-07T09:00+09:00[Asia/Tokyo]",
				"* Meeting 2 at 2017-09-07T09:59:59+09:00[Asia/Tokyo]",
				"  Meeting 3 at 2017-09-07T10:00+09:00[Asia/Tokyo]");
	}

	@Test
	public void testWorld() {
		final Alarm alarm = new Alarm();
		alarm.clock = Clock.fixed(Instant.parse("2017-09-07T00:00:00Z"), ZoneId.of("Asia/Tokyo"));

		alarm.add("Meeting 1", ZonedDateTime.of(LocalDateTime.of(2017, 9, 7, 0, 0, 0), ZoneId.of("UTC")));
		alarm.add("Meeting 2",
				ZonedDateTime.of(LocalDateTime.of(2017, 9, 6, 17, 59, 59), ZoneId.of("America/Los_Angeles")));
		alarm.add("Meeting 3", ZonedDateTime.of(LocalDateTime.of(2017, 9, 7, 3, 0, 0), ZoneId.of("CET")));

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		alarm.print();

		sc.stop();
		sc.assertEquals("* Meeting 1 at 2017-09-07T09:00+09:00[Asia/Tokyo]",
				"* Meeting 2 at 2017-09-07T09:59:59+09:00[Asia/Tokyo]",
				"  Meeting 3 at 2017-09-07T10:00+09:00[Asia/Tokyo]");
	}
}
