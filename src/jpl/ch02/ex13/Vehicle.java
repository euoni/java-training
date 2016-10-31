package jpl.ch02.ex13;

public class Vehicle {
	static int nextId;
	private final int id;
	private double speed;
	private double direction;
	private String owner;

	public Vehicle() {
		id = Vehicle.nextId++;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getId() {
		return id;
	}

	public static int getMaxId() {
		return nextId - 1;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", speed=" + speed + ", direction=" + direction + ", owner=" + owner + "]";
	}
}
