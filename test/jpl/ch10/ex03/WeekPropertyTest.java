package jpl.ch10.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.function.Function;

import org.junit.Test;

import jpl.ch06.ex01.Week;

public class WeekPropertyTest {
	private void checkWeek(Function<Week, Boolean> f) {
		assertThat(f.apply(Week.MONDAY), is(true));
		assertThat(f.apply(Week.TUESDAY), is(true));
		assertThat(f.apply(Week.WEDNESDAY), is(true));
		assertThat(f.apply(Week.THURSDAY), is(true));
		assertThat(f.apply(Week.FRIDAY), is(true));
		assertThat(f.apply(Week.SATURDAY), is(false));
		assertThat(f.apply(Week.SUNDAY), is(false));
	}

	@Test
	public void testWeekProperty() {
		new WeekProperty();
		return;
	}

	@Test
	public void testIsWorkDayIf() {
		checkWeek(WeekProperty::isWorkDayIf);
	}

	@Test
	public void testIsWorkDaySwitch() {
		checkWeek(WeekProperty::isWorkDaySwitch);
	}
}
