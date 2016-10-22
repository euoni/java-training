package jpl.ch02.ex15;

public class Vehicle extends jpl.ch02.ex13.Vehicle {
	public void changeSpeed(double speed) {
		setSpeed(speed);
	}

	public void stop() {
		setSpeed(0.);
	}
}
