package jpl.ch02.ex11;

public class LinkedList extends jpl.ch02.ex08.LinkedList {
	public LinkedList(Object value) {
		super(value);
	}

	public LinkedList(Object value, LinkedList next) {
		super(value, next);
	}

	@Override
	public String toString() {
		return "LinkedList [value=" + value + ", next=" + next + "]";
	}
}
