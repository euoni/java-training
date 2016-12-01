package jpl.ch06.ex02;

public class Vehicle extends jpl.ch02.ex15.Vehicle {
	public enum TurnDirection {
		LEFT, RIGHT
	};

	public void turn(double angle) {
		setDirection(getDirection() + angle);
	}

	public void turn(TurnDirection direction) {
		switch (direction) {
		case LEFT:
			turn(-90.);
			break;
		case RIGHT:
			turn(90.);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}
