package jpl.ch10.ex03;

import jpl.ch06.ex01.Week;

public class WeekProperty {
	public static boolean isWorkDayIf(Week day) {
		if (day == Week.MONDAY)
			return true;
		else if (day == Week.TUESDAY)
			return true;
		else if (day == Week.WEDNESDAY)
			return true;
		else if (day == Week.THURSDAY)
			return true;
		else if (day == Week.FRIDAY)
			return true;
		else if (day == Week.SATURDAY)
			return false;
		else if (day == Week.SUNDAY)
			return false;
		else
			return false;
	}

	public static boolean isWorkDaySwitch(Week day) {
		// better

		switch (day) {
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY:
			return true;
		case SATURDAY:
		case SUNDAY:
			return false;
		default:
			return false;
		}
	}
}
