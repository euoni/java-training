package jpl.ch24.ex03;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateParser {
	public static void parse(String str) {
		final int[] fmtList = new int[] { DateFormat.FULL, DateFormat.LONG, DateFormat.MEDIUM, DateFormat.SHORT };
		Date date = null;
		for (final int fmt : fmtList) {
			try {
				date = DateFormat.getDateInstance(fmt).parse(str);
				break;
			} catch (final ParseException e) {
				continue;
			}
		}
		if (date == null)
			throw new IllegalArgumentException();

		System.out.println("FULL:\t" + DateFormat.getDateInstance(DateFormat.FULL).format(date));
		System.out.println("LONG:\t" + DateFormat.getDateInstance(DateFormat.LONG).format(date));
		System.out.println("MEDIUM:\t" + DateFormat.getDateInstance(DateFormat.MEDIUM).format(date));
		System.out.println("SHORT:\t" + DateFormat.getDateInstance(DateFormat.SHORT).format(date));
	}
}
