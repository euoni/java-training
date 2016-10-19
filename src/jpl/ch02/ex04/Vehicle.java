package jpl.ch02.ex04;

public class Vehicle {
	public static int nextId;
	public final int id;
	public double speed;
	public double direction;
	public String owner;

	public Vehicle() {
		id = Vehicle.nextId++;
	}
}
