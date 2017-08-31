package java8.ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

public class ConditionalTemporalAdjuster {
	public static TemporalAdjuster next(Predicate<LocalDate> f) {
		return w -> {
			LocalDate result = (LocalDate) w;
			do {
				result = result.plusDays(1);
			} while (!f.test(result));
			return result;
		};
	}
}
