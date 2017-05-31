package jpl.ch24.ex02;

import java.util.Currency;
import java.util.Locale;

public class DisplayCurrency {
	static Currency[] cur = new Currency[] { Currency.getInstance("CAD"), Currency.getInstance("CNY"),
			Currency.getInstance("FRF"), Currency.getInstance("DEM"), Currency.getInstance("ITL"),
			Currency.getInstance("JPY") };
	static Locale[] loc = new Locale[] { Locale.CANADA, Locale.CHINA, Locale.FRANCE, Locale.GERMANY, Locale.ITALY,
			Locale.JAPAN };

	public static void main(String[] args) {
		for (final Currency c : cur) {
			for (final Locale l : loc) {
				System.out.printf("%s in %s:\t%s%n", c, l, c.getSymbol(l));
			}
		}
	}
}
