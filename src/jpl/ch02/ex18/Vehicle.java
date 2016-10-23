package jpl.ch02.ex18;

public class Vehicle extends jpl.ch02.ex17.Vehicle {
	public static void main(String[] args) {
		for (final String owner : args) {
			final Vehicle vehicle = new Vehicle();
			vehicle.setOwner(owner);
			System.out.println(vehicle);
		}
	}
}
