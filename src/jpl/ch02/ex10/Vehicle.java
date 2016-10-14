package jpl.ch02.ex10;

public class Vehicle extends jpl.ch02.ex09.Vehicle {
	@Override
	public String toString() {
		return "Vehicle [id=" + getId() + ", speed=" + getSpeed() + ", direction=" + getDirection() + ", owner="
				+ getOwner() + "]";
	}

}
