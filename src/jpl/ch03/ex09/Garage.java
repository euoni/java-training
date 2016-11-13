package jpl.ch03.ex09;

import java.util.Arrays;

import jpl.ch03.ex08.Vehicle;

public class Garage implements Cloneable {
	private final Vehicle[] buffer;
	private int top = -1;

	public Garage(int capacity) {
		buffer = new Vehicle[capacity];
	}

	public void enter(Vehicle vehicle) {
		buffer[++top] = vehicle;
	}

	public Vehicle leave() {
		final Vehicle vehicle = buffer[top];
		buffer[top--] = null;
		return vehicle;
	}

	public static void main(String[] args) {
		final Vehicle v1 = new Vehicle();
		v1.setSpeed(10.);
		final Vehicle v2 = new Vehicle();
		v2.setSpeed(20.);

		final Garage garage = new Garage(5);
		garage.enter(v1);
		garage.enter(v2);
		System.out.println(garage);

		try {
			final Garage cloned = garage.clone();
			System.out.println(cloned);
		} catch (final CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	@Override
	public Garage clone() throws CloneNotSupportedException {
		final Garage other = (Garage) super.clone();

		for (int i = 0; i <= top; i++) {
			other.buffer[i] = buffer[i].clone();
		}

		return other;
	}

	@Override
	public String toString() {
		return "Garage [buffer=" + Arrays.toString(buffer) + "]";
	}
}
