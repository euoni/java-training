package jpl.ch02.ex04;

public class Vehicle {
	private static int nextId;
	public final int id;
	public double speed;
	public double direction;
	public String owner;

	public Vehicle() {
		id = Vehicle.nextId++;
	}
}
