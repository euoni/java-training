package java8.ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DayCount {
	public static long count(LocalDate birthDay) {
		return birthDay.until(LocalDate.now(), ChronoUnit.DAYS);
	}
}
