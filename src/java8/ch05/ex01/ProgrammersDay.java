package java8.ch05.ex01;

import java.time.LocalDate;

public class ProgrammersDay {
	public static LocalDate programmersDay(int year) {
		return LocalDate.ofYearDay(year, 256);
	}
}
