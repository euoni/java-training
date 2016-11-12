package jpl.ch03.ex08;

public class PassengerVehicle extends Vehicle implements Cloneable {
	private final int seats;
	private int passengers;

	public PassengerVehicle(int seats) {
		this.seats = seats;
	}

	public int getSeats() {
		return seats;
	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	@Override
	public PassengerVehicle clone() throws CloneNotSupportedException {
		return (PassengerVehicle) super.clone();
	}
}
