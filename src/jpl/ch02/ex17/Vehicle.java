package jpl.ch02.ex17;

public class Vehicle extends jpl.ch02.ex15.Vehicle {
	public static final char TURN_LEFT = 'l';
	public static final char TURN_RIGHT = 'r';

	public void turn(double angle) {
		setDirection(getDirection() + angle);
	}

	public void turn(char type) {
		switch (type) {
		case TURN_LEFT:
			turn(-90.);
			break;
		case TURN_RIGHT:
			turn(90.);
			break;
		default:
			throw new IllegalArgumentException();
		}
	}
}
