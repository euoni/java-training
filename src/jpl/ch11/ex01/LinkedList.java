package jpl.ch11.ex01;

public class LinkedList<E> {
	private E value;
	private LinkedList<E> next;

	public LinkedList(E value) {
		this.value = value;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public LinkedList<E> getNext() {
		return next;
	}

	public void setNext(LinkedList<E> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "LinkedList [value=" + value + ", next=" + next + "]";
	}
}
