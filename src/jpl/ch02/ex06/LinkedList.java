package jpl.ch02.ex06;

import jpl.ch02.ex05.Vehicle;

public class LinkedList extends jpl.ch02.ex02.LinkedList {
	public static void main(String[] args) {
		final Vehicle car = new Vehicle();
		car.speed = 60;
		car.direction = 180;
		car.owner = "Java";

		final Vehicle train = new Vehicle();
		train.speed = 120;
		train.direction = 0;
		train.owner = "JR";

		final LinkedList head = new LinkedList();
		head.value = car;

		head.next = new LinkedList();
		head.next.value = train;

		System.out.printf("head.value.id = %d%n", ((Vehicle) head.value).id);
		System.out.printf("head.value.speed = %.1f%n", ((Vehicle) head.value).speed);
		System.out.printf("head.value.direction = %.1f%n", ((Vehicle) head.value).direction);
		System.out.printf("head.value.owner = %s%n", ((Vehicle) head.value).owner);

		System.out.printf("head.next.value.id = %d%n", ((Vehicle) head.next.value).id);
		System.out.printf("head.next.value.speed = %.1f%n", ((Vehicle) head.next.value).speed);
		System.out.printf("head.next.value.direction = %.1f%n", ((Vehicle) head.next.value).direction);
		System.out.printf("head.next.value.owner = %s%n", ((Vehicle) head.next.value).owner);
	}
}
