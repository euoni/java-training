package jpl.ch01.ex10;

public class NumericItem {
	private final int number;

	public NumericItem(int number) {
		this.number = number;
	}

	public boolean isEven() {
		return number % 2 == 0;
	}

	public String getMarkedNumber() {
		String mark;
		if (isEven())
			mark = " *";
		else
			mark = "";
		return number + mark;
	}
}
