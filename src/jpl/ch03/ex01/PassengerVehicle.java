package jpl.ch03.ex01;

import jpl.ch02.ex18.Vehicle;

public class PassengerVehicle extends Vehicle {
	private final int seats;
	private int passengers;

	public PassengerVehicle(int seats) {
		if (seats < 0)
			throw new IllegalArgumentException();
		this.seats = seats;
	}

	public int getSeats() {
		return seats;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		if (0 > passengers || seats < passengers)
			throw new IllegalArgumentException();

		this.passengers = passengers;
	}

	public static void main(String[] args) {
		PassengerVehicle vehicle;

		vehicle = new PassengerVehicle(5);
		System.out.println(vehicle);

		vehicle = new PassengerVehicle(7);
		System.out.println(vehicle);
	}

	@Override
	public String toString() {
		return "PassengerVehicle [seats=" + seats + ", passengers=" + passengers + ", getSpeed()=" + getSpeed()
				+ ", getDirection()=" + getDirection() + ", getOwner()=" + getOwner() + ", getId()=" + getId() + "]";
	}
}
