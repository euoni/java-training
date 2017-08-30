package java8.ch05.ex02;

import java.time.LocalDate;

public class YearAddition {
	public static void main(String[] args) {
		final LocalDate date = LocalDate.of(2000, 2, 29);
		System.out.printf("%s + 1 year = %s%n", date, date.plusYears(1));
		System.out.printf("%s + 4 years = %s%n", date, date.plusYears(4));
		System.out.printf("%s + 1 year + 1 year + 1 year + 1 year = %s%n", date,
				date.plusYears(1).plusYears(1).plusYears(1).plusYears(1));
	}
}
