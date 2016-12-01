package jpl.ch06.ex05;

import java.awt.Color;

public enum TrafficLight {
	BLUE {
		@Override
		Color getColor() {
			return Color.BLUE;
		}
	},
	YELLOW {
		@Override
		Color getColor() {
			return Color.YELLOW;
		}
	},
	RED {
		@Override
		Color getColor() {
			return Color.RED;
		}
	};

	abstract Color getColor();
}
