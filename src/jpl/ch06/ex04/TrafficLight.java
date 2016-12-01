package jpl.ch06.ex04;

import java.awt.Color;

public enum TrafficLight {
	BLUE(Color.BLUE), YELLOW(Color.YELLOW), RED(Color.RED);

	Color color;

	TrafficLight(Color color) {
		this.color = color;
	}

	Color getColor() {
		return color;
	}
}
