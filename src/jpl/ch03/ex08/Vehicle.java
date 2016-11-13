package jpl.ch03.ex08;

public class Vehicle implements Cloneable {
	static int nextId;
	private int id;
	private double speed;

	public Vehicle() {
		id = Vehicle.nextId++;
	}

	public int getId() {
		return id;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	@Override
	public Vehicle clone() throws CloneNotSupportedException {
		final Vehicle other = (Vehicle) super.clone();

		other.id = Vehicle.nextId++;

		return other;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", speed=" + speed + "]";
	}
}
