package jpl.ch02.ex05;

public class Vehicle {
	private static int nextId;
	public final int id;
	public double speed;
	public double direction;
	public String owner;

	public Vehicle() {
		id = Vehicle.nextId++;
	}

	public static void main(String[] args) {
		final Vehicle car = new Vehicle();
		car.speed = 60;
		car.direction = 180;
		car.owner = "Java";

		final Vehicle train = new Vehicle();
		train.speed = 120;
		train.direction = 0;
		train.owner = "JR";

		System.out.printf("car.id = %d%n", car.id);
		System.out.printf("car.speed = %.1f%n", car.speed);
		System.out.printf("car.direction = %.1f%n", car.direction);
		System.out.printf("car.owner = %s%n", car.owner);

		System.out.printf("train.id = %d%n", train.id);
		System.out.printf("train.speed = %.1f%n", train.speed);
		System.out.printf("train.direction = %.1f%n", train.direction);
		System.out.printf("train.owner = %s%n", train.owner);
	}
}
