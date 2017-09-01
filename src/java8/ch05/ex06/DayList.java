package java8.ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java8.ch05.ex03.ConditionalTemporalAdjuster;

public class DayList {
	public static List<LocalDate> listFridayThe13th(LocalDate from, LocalDate end) {
		final int THE_DAY = 13;

		if (from.getDayOfMonth() != THE_DAY)
			from = from.with(ConditionalTemporalAdjuster.next(w -> w.getDayOfMonth() == THE_DAY));

		final List<LocalDate> result = new ArrayList<>();
		for (LocalDate d = from; d.isBefore(end); d = d.plusMonths(1)) {
			assert d.getDayOfMonth() == THE_DAY;
			if (d.getDayOfWeek() == DayOfWeek.FRIDAY)
				result.add(d);
		}

		return result;
	}

	public static void main(String[] args) {
		for (final LocalDate d : listFridayThe13th(LocalDate.of(1901, 1, 1), LocalDate.of(2001, 1, 1))) {
			System.out.println(d);
		}
	}
}
