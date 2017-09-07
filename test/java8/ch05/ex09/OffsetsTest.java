package java8.ch05.ex09;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import org.junit.Test;

import jp.ne.sonet.ca2.yshibata.test.StdoutCapture;

public class OffsetsTest {
	@Test
	public void testCtor() {
		new Offsets();
	}

	@Test
	public void testMain() {
		Offsets.clock = Clock.fixed(Instant.ofEpochSecond(1504444038L), ZoneId.of("Asia/Tokyo"));

		final StdoutCapture sc = new StdoutCapture();
		sc.start();

		Offsets.main(null);

		sc.stop();
		sc.assertEquals("America/Caracas: -04:30", "America/St_Johns: -02:30", "Asia/Calcutta: +05:30",
				"Asia/Colombo: +05:30", "Asia/Kabul: +04:30", "Asia/Kathmandu: +05:45", "Asia/Katmandu: +05:45",
				"Asia/Kolkata: +05:30", "Asia/Pyongyang: +08:30", "Asia/Rangoon: +06:30", "Asia/Tehran: +04:30",
				"Australia/Adelaide: +09:30", "Australia/Broken_Hill: +09:30", "Australia/Darwin: +09:30",
				"Australia/Eucla: +08:45", "Australia/LHI: +10:30", "Australia/Lord_Howe: +10:30",
				"Australia/North: +09:30", "Australia/South: +09:30", "Australia/Yancowinna: +09:30",
				"Canada/Newfoundland: -02:30", "Indian/Cocos: +06:30", "Iran: +04:30", "NZ-CHAT: +12:45",
				"Pacific/Chatham: +12:45", "Pacific/Marquesas: -09:30");
	}
}
