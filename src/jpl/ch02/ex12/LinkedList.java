package jpl.ch02.ex12;

public class LinkedList {
	private Object value;
	private LinkedList next;

	public LinkedList(Object value) {
		this(value, null);
	}

	public LinkedList(Object value, LinkedList next) {
		this.value = value;
		this.next = next;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public LinkedList getNext() {
		return next;
	}

	public void setNext(LinkedList next) {
		this.next = next;
	}

	public void setNext(LinkedList... items) {
		LinkedList prev = this;
		for (final LinkedList item : items) {
			prev.setNext(item);
			prev = item;
		}
	}

	@Override
	public String toString() {
		return "LinkedList [value=" + value + ", next=" + next + "]";
	}
}
