package java8.ch05.ex04;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Cal {
	private final int year;
	private final int month;

	public Cal(int year, int month) {
		this.year = year;
		this.month = month;
	}

	@Override
	public String toString() {
		LocalDate date = LocalDate.of(year, month, 1);
		String result = "";
		for (int i = 0; i < date.getDayOfWeek().getValue() - 1; i++)
			result += "   ";
		while (true) {
			result += String.format("%2d ", date.getDayOfMonth());
			date = date.plusDays(1);
			if (date.getMonth().getValue() == month) {
				if (date.getDayOfWeek() == DayOfWeek.MONDAY)
					result += System.lineSeparator();
			} else
				break;
		}
		return result;
	}

	public static void main(String[] args) {
		if (args.length == 2)
			try {
				System.out.println(new Cal(Integer.parseInt(args[1]), Integer.parseInt(args[0])).toString());
			} catch (final NumberFormatException e) {
				System.out.println("Invalid argument");
			}
		else
			System.out.println("Usage: java Cal 3 2013");
	}
}
