package jpl.ch03.ex10;

public class LinkedList extends jpl.ch02.ex16.LinkedList implements Cloneable {
	public LinkedList(Object value) {
		super(value);
	}

	@Override
	public LinkedList clone() throws CloneNotSupportedException {
		return (LinkedList) super.clone();
	}
}
