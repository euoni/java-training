package jpl.ch02.ex07;

public class Vehicle extends jpl.ch02.ex04.Vehicle {
	public Vehicle() {
		super();
	}

	public Vehicle(final String owner) {
		this();
		this.owner = owner;
	}

	public static void main(String[] args) {
		final Vehicle car = new Vehicle("Java");
		car.speed = 60;
		car.direction = 180;

		final Vehicle train = new Vehicle("JR");
		train.speed = 120;
		train.direction = 0;

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
