package jpl.ch02.ex14;

public class LinkedList {
	private Object value;
	private LinkedList next;

	public LinkedList(Object value) {
		this.value = value;
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

	public void setNext(LinkedList... items) {
		LinkedList prev = this;
		for (final LinkedList item : items) {
			prev.next = item;
			prev = item;
		}
	}

	@Override
	public String toString() {
		return "LinkedList [value=" + value + ", next=" + next + "]";
	}
}
