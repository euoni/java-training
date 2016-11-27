package jpl.ch04.ex03;

public class LinkedListImpl implements LinkedList {
	private Object value;
	private LinkedList next;

	public LinkedListImpl(Object value) {
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public LinkedList getNext() {
		return next;
	}

	@Override
	public void setNext(LinkedList next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "LinkedListImpl [value=" + value + ", next=" + next + "]";
	}
}
