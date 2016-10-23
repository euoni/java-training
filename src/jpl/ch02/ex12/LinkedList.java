package jpl.ch02.ex12;

public class LinkedList extends jpl.ch02.ex11.LinkedList {
	public LinkedList(Object value) {
		super(value);
	}

	public LinkedList(Object value, LinkedList next) {
		super(value, next);
	}

	public void setNext(LinkedList... items) {
		LinkedList prev = this;
		for (final LinkedList item : items) {
			prev.next = item;
			prev = item;
		}
	}
}
